package com.sabigny.plmpruebatecnica.auth.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.sabigny.plmpruebatecnica.auth.domain.model.*
import com.sabigny.plmpruebatecnica.auth.domain.usecases.GetCountriesUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.GetProfessionsUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.GetStatesByCountryUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.RegisterUserUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.RegisterValidationUseCases
import com.sabigny.plmpruebatecnica.auth.domain.usecases.SaveUserCodeUseCase
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getStatesByCountryUseCase: GetStatesByCountryUseCase,
    private val getProfessionsUseCase: GetProfessionsUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val saveUserCodeUseCase: SaveUserCodeUseCase,
    private val validationUseCases: RegisterValidationUseCases
) : ViewModel() {

    var state by mutableStateOf(RegistrationState())
        private set

    init {
        loadInitialData()
    }

    fun onEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.EmailChanged -> {
                state = state.copy(email = event.email, emailError = null)
            }
            is RegistrationEvent.FirstNameChanged -> {
                state = state.copy(firstName = event.firstName, firstNameError = null)
            }
            is RegistrationEvent.LastNameChanged -> {
                state = state.copy(lastName = event.lastName, lastNameError = null)
            }
            is RegistrationEvent.MaternalLastNameChanged -> {
                state = state.copy(maternalLastName = event.maternalLastName, maternalLastNameError = null)
            }
            is RegistrationEvent.PhoneChanged -> {
                state = state.copy(phone = event.phone, phoneError = null)
            }
            is RegistrationEvent.ProfessionalLicenseChanged -> {
                state = state.copy(professionalLicense = event.license, professionalLicenseError = null)
            }
            is RegistrationEvent.ProfessionSelected -> {
                state = state.copy(
                    selectedProfession = event.profession,
                    professionError = null,
                    isProfessionalLicenseRequired = event.profession?.requiresLicense ?: false
                )
            }
            is RegistrationEvent.CountrySelected -> {
                state = state.copy(
                    selectedCountry = event.country,
                    countryError = null,
                    selectedState = null,
                    states = emptyList(),
                    stateError = null
                )
                event.country?.id?.let { loadStatesByCountry(it) }
            }
            is RegistrationEvent.StateSelected -> {
                state = state.copy(selectedState = event.state, stateError = null)
            }
            is RegistrationEvent.SubmitRegistration -> {
                registerUser()
            }
            is RegistrationEvent.DismissError -> {
                state = state.copy(error = null)
            }
        }
    }

    private fun loadInitialData() {
        loadCountries()
        loadProfessions()
    }

    private fun loadCountries() {
        viewModelScope.launch {
            state = state.copy(isLoadingCountries = true, error = null)
            getCountriesUseCase().collect { result ->
                result.fold(
                    onSuccess = { countries ->
                        state = state.copy(countries = countries, isLoadingCountries = false)
                    },
                    onFailure = { error ->
                        state = state.copy(
                            error = "Error al cargar países: ${error.message}",
                            isLoadingCountries = false
                        )
                    }
                )
            }
        }
    }

    private fun loadStatesByCountry(countryId: Int) {
        viewModelScope.launch {
            state = state.copy(isLoadingStates = true, error = null)
            getStatesByCountryUseCase(countryId).collect { result ->
                result.fold(
                    onSuccess = { states ->
                        state = state.copy(states = states, isLoadingStates = false)
                    },
                    onFailure = { error ->
                        state = state.copy(
                            error = "Error al cargar estados: ${error.message}",
                            isLoadingStates = false
                        )
                    }
                )
            }
        }
    }

    private fun loadProfessions() {
        viewModelScope.launch {
            state = state.copy(isLoadingProfessions = true, error = null)
            getProfessionsUseCase().collect { result ->
                result.fold(
                    onSuccess = { professions ->
                        state = state.copy(professions = professions, isLoadingProfessions = false)
                    },
                    onFailure = { error ->
                        state = state.copy(
                            error = "Error al cargar profesiones: ${error.message}",
                            isLoadingProfessions = false
                        )
                    }
                )
            }
        }
    }

    private fun registerUser() {
        state = state.copy(
            emailError = null,
            firstNameError = null,
            lastNameError = null,
            maternalLastNameError = null,
            phoneError = null,
            professionError = null,
            professionalLicenseError = null,
            countryError = null,
            stateError = null,
            error = null
        )

        val validationErrors = mapOf(
            "emailError" to if (!validationUseCases.validateEmailUseCase(state.email))
                "El email no es válido" else null,
            "firstNameError" to if (!validationUseCases.validateTextOnlyUseCase(state.firstName))
                "El nombre solo debe contener letras" else null,
            "lastNameError" to if (!validationUseCases.validateTextOnlyUseCase(state.lastName))
                "El apellido paterno solo debe contener letras" else null,
            "maternalLastNameError" to if (!validationUseCases.validateTextOnlyUseCase(state.maternalLastName))
                "El apellido materno solo debe contener letras" else null,
            "phoneError" to if (!validationUseCases.validatePhoneUseCase(state.phone))
                "El teléfono debe ser numérico y máximo 10 dígitos" else null,
            "professionError" to if (!validationUseCases.validateProfessionUseCase(state.selectedProfession?.id ?: -1, state.professions))
                "Selecciona una profesión válida" else null,
            "professionalLicenseError" to if (!validationUseCases.validateProfessionalIdUseCase(state.professionalLicense, state.isProfessionalLicenseRequired))
                "La cédula debe ser alfanumérica y máximo 10 caracteres" else null,
            "countryError" to if (!validationUseCases.validateCountryUseCase(state.selectedCountry?.id ?: -1, state.countries))
                "Selecciona un país válido" else null,
            "stateError" to if (!validationUseCases.validateStateUseCase(state.selectedState?.shortName ?: "", state.states))
                "Selecciona un estado válido" else null
        )

        state = state.copy(
            emailError = validationErrors["emailError"],
            firstNameError = validationErrors["firstNameError"],
            lastNameError = validationErrors["lastNameError"],
            maternalLastNameError = validationErrors["maternalLastNameError"],
            phoneError = validationErrors["phoneError"],
            professionError = validationErrors["professionError"],
            professionalLicenseError = validationErrors["professionalLicenseError"],
            countryError = validationErrors["countryError"],
            stateError = validationErrors["stateError"]
        )

        if (validationErrors.values.all { it == null }) {
            state = state.copy(isSubmitting = true)
            viewModelScope.launch {
                val userInfo = UserRegistrationInfo(
                    email = state.email,
                    firstName = state.firstName,
                    lastName = state.lastName,
                    maternalLastName = state.maternalLastName.takeUnless { it.isBlank() },
                    phone = state.phone.takeUnless { it.isBlank() },
                    profession = state.selectedProfession?.id ?: -1,
                    professionalLicense = state.professionalLicense.takeUnless { it.isBlank() },
                    stateShortName = state.selectedState?.shortName ?: ""
                )

                registerUserUseCase(userInfo).fold(
                    onSuccess = { userCode ->
                        saveUserCodeUseCase(userCode.id)
                        state = state.copy(
                            isSubmitting = false,
                            registrationCompleted = true,
                            userCode = userCode
                        )
                    },
                    onFailure = { error ->
                        state = state.copy(
                            isSubmitting = false,
                            error = "Error en el registro: ${error.message}"
                        )
                    }
                )
            }
        }
    }
}