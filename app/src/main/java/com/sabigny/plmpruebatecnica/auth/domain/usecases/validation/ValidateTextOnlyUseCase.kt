package com.sabigny.plmpruebatecnica.auth.domain.usecases.validation

class ValidateTextOnlyUseCase {
    operator fun invoke(text: String): Boolean {
        return text.isNotBlank() && text.all { it.isLetter() }
    }
}