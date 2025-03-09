package com.sabigny.plmpruebatecnica.auth.domain.usecases

import com.sabigny.plmpruebatecnica.auth.domain.model.Country
import com.sabigny.plmpruebatecnica.auth.domain.repository.RegistrationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val repository: RegistrationRepository
) {
    suspend operator fun invoke(): Flow<Result<List<Country>>> {
        return repository.getCountries()
    }
}
