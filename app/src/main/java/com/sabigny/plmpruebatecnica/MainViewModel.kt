package com.sabigny.plmpruebatecnica

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabigny.plmpruebatecnica.core.usecases.GetUserCodeUseCase
import com.sabigny.plmpruebatecnica.navigation.NavigationRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// MainViewModel
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserCodeUseCase: GetUserCodeUseCase
) : ViewModel() {

    private val _isUserRegistered = MutableStateFlow<Boolean?>(null)
    val isUserRegistered = _isUserRegistered.asStateFlow()

    init {
        viewModelScope.launch {
            getUserCodeUseCase().collect { userCode ->
                _isUserRegistered.value = !userCode.isNullOrEmpty()
                Log.d("MainViewModel", "UserCode encontrado: $userCode, isRegistered: ${_isUserRegistered.value}")
            }
        }
    }

    fun getStartDestination(): NavigationRoute {
        return if (_isUserRegistered.value == true) {
            NavigationRoute.Search
        } else {
            NavigationRoute.Login
        }
    }
}