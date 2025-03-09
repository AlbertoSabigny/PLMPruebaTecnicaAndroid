package com.sabigny.plmpruebatecnica.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabigny.plmpruebatecnica.core.usecases.GetUserCodeUseCase
import com.sabigny.plmpruebatecnica.search.domain.usescases.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val getUserCodeUseCase: GetUserCodeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<SearchState>(SearchState.Idle)
    val state: StateFlow<SearchState> = _state

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private var userCode: String? = null

    init {
        loadUserCode()
    }

    private fun loadUserCode() {
        viewModelScope.launch {
            getUserCodeUseCase().collect { code ->
                userCode = code
            }
        }
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnSearchQueryChange -> {
                _searchQuery.value = event.query
            }

            is SearchEvent.OnSearchSubmit -> {
                searchDrugs()
            }
        }
    }

    private fun searchDrugs() {
        viewModelScope.launch {
            _state.value = SearchState.Loading
            delay(500)

            val code = userCode ?: return@launch

            val resultWrapper = searchUseCase(code, _searchQuery.value.trim())

            resultWrapper.fold(
                onSuccess = { drugs ->
                    _state.value = when {
                        drugs.isEmpty() -> SearchState.Empty
                        else -> SearchState.Success(drugs)
                    }
                },
                onFailure = { exception ->
                    println("Error: ${exception.javaClass.simpleName} - ${exception.message}")

                    _state.value = when (exception) {
                        is HttpException -> when (exception.code()) {
                            503 -> SearchState.ServerUnavailable
                            else -> SearchState.Error
                        }
                        else -> SearchState.Error
                    }
                }
            )
        }
    }
}
