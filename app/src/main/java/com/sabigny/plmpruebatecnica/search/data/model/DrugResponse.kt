package com.sabigny.plmpruebatecnica.search.data.model

import com.google.gson.annotations.SerializedName

data class DrugResponse(
    @SerializedName("Brand") val brand: String?,
    @SerializedName("PharmaForm") val pharmaForm: String?
)

