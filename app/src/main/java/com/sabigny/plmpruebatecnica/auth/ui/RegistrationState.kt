package com.sabigny.plmpruebatecnica.auth.ui

import com.sabigny.plmpruebatecnica.auth.domain.model.Country
import com.sabigny.plmpruebatecnica.auth.domain.model.Profession
import com.sabigny.plmpruebatecnica.auth.domain.model.State
import com.sabigny.plmpruebatecnica.auth.domain.model.UserCode

data class RegistrationState(
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val maternalLastName: String = "",
    val phone: String = "",
    val professionalLicense: String = "",
    val selectedProfession: Profession? = null,
    val selectedCountry: Country? = null,
    val selectedState: State? = null,
    val countries: List<Country> = emptyList(),
    val states: List<State> = emptyList(),
    val professions: List<Profession> = emptyList(),
    val emailError: String? = null,
    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val maternalLastNameError: String? = null,
    val phoneError: String? = null,
    val professionError: String? = null,
    val professionalLicenseError: String? = null,
    val countryError: String? = null,
    val stateError: String? = null,
    val isLoadingCountries: Boolean = false,
    val isLoadingStates: Boolean = false,
    val isLoadingProfessions: Boolean = false,
    val isSubmitting: Boolean = false,
    val isProfessionalLicenseRequired: Boolean = false,
    val registrationCompleted: Boolean = false,
    val userCode: UserCode? = null,
    val error: String? = null
)




