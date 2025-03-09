package com.sabigny.plmpruebatecnica.search.domain.usescases

import com.sabigny.plmpruebatecnica.search.domain.model.Drug
import com.sabigny.plmpruebatecnica.search.domain.repository.SearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(code: String, query: String): Result<List<Drug>> {
        return repository.getDrugs(code, query)
    }
}
