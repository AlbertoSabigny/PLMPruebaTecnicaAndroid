package com.sabigny.plmpruebatecnica.auth.data.repository

import com.sabigny.plmpruebatecnica.auth.data.model.CountryDto
import com.sabigny.plmpruebatecnica.auth.data.model.ProfessionDto
import com.sabigny.plmpruebatecnica.auth.data.model.StateDto
import com.sabigny.plmpruebatecnica.auth.data.model.UserRegistrationResponse
import com.sabigny.plmpruebatecnica.auth.data.model.request.UserRegistrationRequest
import com.sabigny.plmpruebatecnica.auth.data.remote.RegistrationApiService
import com.sabigny.plmpruebatecnica.auth.domain.model.Country
import com.sabigny.plmpruebatecnica.auth.domain.model.Profession
import com.sabigny.plmpruebatecnica.auth.domain.model.State
import com.sabigny.plmpruebatecnica.auth.domain.model.UserCode
import com.sabigny.plmpruebatecnica.auth.domain.model.UserRegistrationInfo
import com.sabigny.plmpruebatecnica.auth.domain.repository.RegistrationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val apiService: RegistrationApiService
) : RegistrationRepository {

    override suspend fun getCountries(): Flow<Result<List<Country>>> = flow {
        try {
            val response = apiService.getCountries()
            emit(Result.success(response.getCountriesResult.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun getStatesByCountry(countryId: Int): Flow<Result<List<State>>> = flow {
        try {
            val response = apiService.getStatesByCountry(countryId)
            emit(Result.success(response.getStateByCountryResult.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun getProfessions(): Flow<Result<List<Profession>>> = flow {
        try {
            val response = apiService.getProfessions()
            emit(Result.success(response.getProfessionsResult.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun registerUser(userInfo: UserRegistrationInfo): Result<UserCode> {
        return try {
            val request = userInfo.toUserRegistrationRequest()


            val response = apiService.registerUser(request)


            if (response.userId.isNotEmpty()) {
                val userCode = response.toUserCode()
                Result.success(userCode)
            } else {
                Result.failure(Exception("Error: Respuesta vacía del servidor"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun UserRegistrationInfo.toUserRegistrationRequest(): UserRegistrationRequest {
        return UserRegistrationRequest(
            email = this.email,
            firstName = this.firstName,
            lastName = this.lastName,
            slastName = this.maternalLastName,
            phone = this.phone,
            profession = this.profession,
            professionLicense = this.professionalLicense,
            state = this.stateShortName
        )
    }

    private fun UserRegistrationResponse.toUserCode(): UserCode {
        return UserCode(id = this.userId)
    }

    private fun CountryDto.toDomain(): Country {
        return Country(
            id = CountryId ?: 0,
            name = CountryName ?: "",
            code = CountryCode ?: "",
            lada = CountryLada ?: "",
            active = Active ?: false
        )
    }

    private fun StateDto.toDomain(): State {
        return State(
            id = StateId ?: -1,
            name = StateName ?: "",
            shortName = ShortName ?: "",
            countryId = CountryId ?: 0,
            active = Active ?: false
        )
    }

    private fun ProfessionDto.toDomain(): Profession {
        return Profession(
            id = ProfessionId ?: -1,
            name = ProfessionName ?: "",
            englishName = EnglishName ?: "",
            parentId = ParentId,
            requiresLicense = ReqProfessionalLicense ?: false,
            active = Active ?: false
        )
    }
}