package com.sabigny.plmpruebatecnica.search.data.mappers

import com.sabigny.plmpruebatecnica.search.data.model.DrugResponse
import com.sabigny.plmpruebatecnica.search.domain.model.Drug

object SearchMapper {
    fun DrugResponse.toDomain(): Drug {
        return Drug(
            name = this.brand ?: "Nombre desconocido",
            form = this.pharmaForm ?: "Forma desconocida"
        )
    }
}
