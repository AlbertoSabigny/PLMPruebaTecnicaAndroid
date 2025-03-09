package com.sabigny.plmpruebatecnica.auth.domain.usecases

import com.sabigny.plmpruebatecnica.auth.domain.usecases.validation.ValidateCountryUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.validation.ValidateEmailUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.validation.ValidatePhoneUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.validation.ValidateProfessionUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.validation.ValidateProfessionalIdUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.validation.ValidateStateUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.validation.ValidateTextOnlyUseCase

data class RegisterValidationUseCases(
    val validateEmailUseCase: ValidateEmailUseCase,
    val validateTextOnlyUseCase: ValidateTextOnlyUseCase,
    val validatePhoneUseCase: ValidatePhoneUseCase,
    val validateProfessionUseCase: ValidateProfessionUseCase,
    val validateProfessionalIdUseCase: ValidateProfessionalIdUseCase,
    val validateCountryUseCase: ValidateCountryUseCase,
    val validateStateUseCase: ValidateStateUseCase
)