package com.sabigny.plmpruebatecnica.auth.data.model

data class ProfessionResponse(
    val getProfessionsResult: List<ProfessionDto>
)

data class ProfessionDto(
    val Active: Boolean?,
    val EnglishName: String?,
    val ParentId: Int?,
    val ProfessionId: Int?,
    val ProfessionName: String?,
    val ReqProfessionalLicense: Boolean?
)