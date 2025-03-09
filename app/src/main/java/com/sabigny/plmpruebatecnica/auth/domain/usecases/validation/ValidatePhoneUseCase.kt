package com.sabigny.plmpruebatecnica.auth.domain.usecases.validation

class ValidatePhoneUseCase {
    operator fun invoke(phone: String): Boolean {
        return phone.isNotBlank() && phone.all { it.isDigit() } && phone.length <= 10
    }
}