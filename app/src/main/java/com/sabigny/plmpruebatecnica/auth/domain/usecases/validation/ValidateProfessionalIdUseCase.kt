package com.sabigny.plmpruebatecnica.auth.domain.usecases.validation

class ValidateProfessionalIdUseCase {
    operator fun invoke(professionalId: String, isRequired: Boolean): Boolean {
        return if (isRequired) {
            professionalId.isNotBlank() && professionalId.length <= 10 && professionalId.all { it.isLetterOrDigit() }
        } else {
            professionalId.isBlank() || (professionalId.length <= 10 && professionalId.all { it.isLetterOrDigit() })
        }
    }
}