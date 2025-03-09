package com.sabigny.plmpruebatecnica.auth.domain.usecases.validation

import com.sabigny.plmpruebatecnica.auth.domain.model.Profession

class ValidateProfessionUseCase {
    operator fun invoke(professionId: Int, availableProfessions: List<Profession>): Boolean {
        return professionId != -1 && availableProfessions.any { it.id == professionId }
    }
}