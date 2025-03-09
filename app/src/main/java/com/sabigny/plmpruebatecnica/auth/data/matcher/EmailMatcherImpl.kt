package com.sabigny.plmpruebatecnica.auth.data.matcher

import android.util.Patterns
import com.sabigny.plmpruebatecnica.auth.domain.matcher.EmailMatcher

class EmailMatcherImpl : EmailMatcher {
    override fun isValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}