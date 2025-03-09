package com.sabigny.plmpruebatecnica.auth.domain.usecases.validation

import com.sabigny.plmpruebatecnica.auth.domain.matcher.EmailMatcher

class ValidateEmailUseCase(private val emailMatcher: EmailMatcher) {
    operator fun invoke(email: String): Boolean {
        return emailMatcher.isValid(email)
    }
}