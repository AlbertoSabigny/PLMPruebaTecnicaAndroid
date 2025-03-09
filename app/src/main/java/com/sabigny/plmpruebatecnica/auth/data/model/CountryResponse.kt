package com.sabigny.plmpruebatecnica.auth.data.model

data class CountryResponse(
    val getCountriesResult: List<CountryDto>
)

data class CountryDto(
    val Active: Boolean?,
    val CountryCode: String?,
    val CountryId: Int?,
    val CountryLada: String?,
    val CountryName: String?,
    val ID: String?
)