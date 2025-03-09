package com.sabigny.plmpruebatecnica.auth.domain.usecases.validation

import com.sabigny.plmpruebatecnica.auth.domain.model.Country

class ValidateCountryUseCase {
    operator fun invoke(countryId: Int, availableCountries: List<Country>): Boolean {
        return countryId != -1 && availableCountries.any { it.id == countryId }
    }
}