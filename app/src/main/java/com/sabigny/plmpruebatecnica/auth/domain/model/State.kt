package com.sabigny.plmpruebatecnica.auth.domain.model

data class State(
    val id: Int,
    val name: String,
    val shortName: String,
    val countryId: Int,
    val active: Boolean
)