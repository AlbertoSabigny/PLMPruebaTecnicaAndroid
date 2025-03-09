package com.sabigny.plmpruebatecnica.auth.domain.model

data class Profession(
    val id: Int,
    val name: String,
    val englishName: String,
    val parentId: Int?,
    val requiresLicense: Boolean,
    val active: Boolean
)