package com.sabigny.plmpruebatecnica.auth.domain.usecases.validation

import com.sabigny.plmpruebatecnica.auth.domain.model.State

class ValidateStateUseCase {
    operator fun invoke(stateShortName: String, availableStates: List<State>): Boolean {
        return stateShortName.isNotBlank() && availableStates.any { it.shortName == stateShortName }
    }
}