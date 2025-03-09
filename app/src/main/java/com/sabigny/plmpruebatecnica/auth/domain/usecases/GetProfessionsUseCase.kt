package com.sabigny.plmpruebatecnica.auth.domain.usecases

import com.sabigny.plmpruebatecnica.auth.domain.model.Profession
import com.sabigny.plmpruebatecnica.auth.domain.repository.RegistrationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProfessionsUseCase @Inject constructor(
    private val repository: RegistrationRepository
) {
    suspend operator fun invoke(): Flow<Result<List<Profession>>> {
        return repository.getProfessions()
    }
}
