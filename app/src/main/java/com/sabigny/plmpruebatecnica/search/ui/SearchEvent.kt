package com.sabigny.plmpruebatecnica.search.ui

sealed class SearchEvent {
    data class OnSearchQueryChange(val query: String) : SearchEvent()
    object OnSearchSubmit : SearchEvent()
}
