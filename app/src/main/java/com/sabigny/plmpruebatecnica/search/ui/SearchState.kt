package com.sabigny.plmpruebatecnica.search.ui

import com.sabigny.plmpruebatecnica.search.domain.model.Drug

sealed class SearchState {
    object Idle : SearchState()
    object Loading : SearchState()
    data class Success(val drugs: List<Drug>) : SearchState()
    object Empty : SearchState()
    object ServerUnavailable : SearchState()
    object Error : SearchState()
}
