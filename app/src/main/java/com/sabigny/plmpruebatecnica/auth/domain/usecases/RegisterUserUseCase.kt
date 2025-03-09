package com.sabigny.plmpruebatecnica.auth.domain.usecases

import com.sabigny.plmpruebatecnica.auth.domain.model.UserCode
import com.sabigny.plmpruebatecnica.auth.domain.model.UserRegistrationInfo
import com.sabigny.plmpruebatecnica.auth.domain.repository.RegistrationRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val repository: RegistrationRepository
) {
    suspend operator fun invoke(userInfo: UserRegistrationInfo): Result<UserCode> {
        return repository.registerUser(userInfo)
    }
}
