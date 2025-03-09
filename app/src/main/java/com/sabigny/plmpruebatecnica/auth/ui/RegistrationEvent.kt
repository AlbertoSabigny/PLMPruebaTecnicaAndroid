package com.sabigny.plmpruebatecnica.auth.ui

import com.sabigny.plmpruebatecnica.auth.domain.model.Country
import com.sabigny.plmpruebatecnica.auth.domain.model.Profession
import com.sabigny.plmpruebatecnica.auth.domain.model.State

sealed class RegistrationEvent {
    data class EmailChanged(val email: String) : RegistrationEvent()
    data class FirstNameChanged(val firstName: String) : RegistrationEvent()
    data class LastNameChanged(val lastName: String) : RegistrationEvent()
    data class MaternalLastNameChanged(val maternalLastName: String) : RegistrationEvent()
    data class PhoneChanged(val phone: String) : RegistrationEvent()
    data class ProfessionalLicenseChanged(val license: String) : RegistrationEvent()
    data class ProfessionSelected(val profession: Profession?) : RegistrationEvent()
    data class CountrySelected(val country: Country?) : RegistrationEvent()
    data class StateSelected(val state: State?) : RegistrationEvent()
    object SubmitRegistration : RegistrationEvent()
    object DismissError : RegistrationEvent()
}