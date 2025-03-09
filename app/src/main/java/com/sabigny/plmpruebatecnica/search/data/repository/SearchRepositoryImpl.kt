package com.sabigny.plmpruebatecnica.search.data.repository

import com.sabigny.plmpruebatecnica.search.data.mappers.SearchMapper.toDomain
import com.sabigny.plmpruebatecnica.search.data.remote.DrugApiService
import com.sabigny.plmpruebatecnica.search.domain.model.Drug
import com.sabigny.plmpruebatecnica.search.domain.repository.SearchRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val apiService: DrugApiService
) : SearchRepository {

    override suspend fun getDrugs(code: String, drugName: String): Result<List<Drug>> {
        return try {
            val drugs = apiService.getDrugs(
                code = code,
                countryId = "MEX",
                editionId = 211,
                drug = drugName
            ).map { it.toDomain() }
            Result.success(drugs)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

