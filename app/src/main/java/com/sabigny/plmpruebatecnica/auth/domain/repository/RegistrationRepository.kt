package com.sabigny.plmpruebatecnica.auth.domain.repository

import com.sabigny.plmpruebatecnica.auth.domain.model.Country
import com.sabigny.plmpruebatecnica.auth.domain.model.Profession
import com.sabigny.plmpruebatecnica.auth.domain.model.State
import com.sabigny.plmpruebatecnica.auth.domain.model.UserCode
import com.sabigny.plmpruebatecnica.auth.domain.model.UserRegistrationInfo
import kotlinx.coroutines.flow.Flow

interface RegistrationRepository {
    suspend fun getCountries(): Flow<Result<List<Country>>>
    suspend fun getStatesByCountry(countryId: Int): Flow<Result<List<State>>>
    suspend fun getProfessions(): Flow<Result<List<Profession>>>
    suspend fun registerUser(userInfo: UserRegistrationInfo): Result<UserCode>
}