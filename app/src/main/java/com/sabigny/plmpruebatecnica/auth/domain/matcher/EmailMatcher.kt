package com.sabigny.plmpruebatecnica.auth.domain.matcher

interface EmailMatcher {
    fun isValid(email: String): Boolean
}