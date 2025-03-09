package com.sabigny.plmpruebatecnica.auth.domain.model

data class Country(
    val id: Int,
    val name: String,
    val code: String,
    val lada: String,
    val active: Boolean
)