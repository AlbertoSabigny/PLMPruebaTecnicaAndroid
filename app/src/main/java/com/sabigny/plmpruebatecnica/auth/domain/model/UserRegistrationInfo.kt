package com.sabigny.plmpruebatecnica.auth.domain.model

data class UserRegistrationInfo(
    val email: String,
    val firstName: String,
    val lastName: String,
    val maternalLastName: String?,
    val phone: String?,
    val profession: Int,
    val professionalLicense: String?,
    val stateShortName: String
)