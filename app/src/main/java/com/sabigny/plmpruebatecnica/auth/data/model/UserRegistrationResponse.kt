package com.sabigny.plmpruebatecnica.auth.data.model

import com.google.gson.annotations.SerializedName

data class UserRegistrationResponse(
    @SerializedName("saveMobileLocationAppClientResult") val userId: String
)
