package com.sabigny.plmpruebatecnica.auth.data.model

data class StateResponse(
    val getStateByCountryResult: List<StateDto>
)

data class StateDto(
    val Active: Boolean?,
    val CountryId: Int?,
    val ShortName: String?,
    val StateId: Int?,
    val StateName: String?
)