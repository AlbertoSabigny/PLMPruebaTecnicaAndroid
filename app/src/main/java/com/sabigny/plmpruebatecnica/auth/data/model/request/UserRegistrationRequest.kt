package com.sabigny.plmpruebatecnica.auth.data.model.request

data class UserRegistrationRequest(
    val codePrefix: String = "TESTPLM",
    val country: Int =1,
    val email: String,
    val firstName: String,
    val lastName: String,
    val phone: String?,
    val profession: Int,
    val professionLicense: String?,
    val slastName: String?,
    val source: Int = 27,
    val state: String,
    val targetOutput: Int = 5
)