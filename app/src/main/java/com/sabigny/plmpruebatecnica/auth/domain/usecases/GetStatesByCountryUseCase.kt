package com.sabigny.plmpruebatecnica.auth.domain.usecases

import com.sabigny.plmpruebatecnica.auth.domain.model.State
import com.sabigny.plmpruebatecnica.auth.domain.repository.RegistrationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStatesByCountryUseCase @Inject constructor(
    private val repository: RegistrationRepository
) {
    suspend operator fun invoke(countryId: Int): Flow<Result<List<State>>> {
        return repository.getStatesByCountry(countryId)
    }
}
