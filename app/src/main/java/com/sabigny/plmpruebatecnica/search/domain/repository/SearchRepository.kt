package com.sabigny.plmpruebatecnica.search.domain.repository

import com.sabigny.plmpruebatecnica.search.domain.model.Drug

interface SearchRepository {
    suspend fun getDrugs(code: String, drugName: String): Result<List<Drug>>
}
