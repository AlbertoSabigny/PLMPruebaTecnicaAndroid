package com.sabigny.plmpruebatecnica.auth.data.remote

import com.sabigny.plmpruebatecnica.auth.data.model.CountryResponse
import com.sabigny.plmpruebatecnica.auth.data.model.ProfessionResponse
import com.sabigny.plmpruebatecnica.auth.data.model.StateResponse
import com.sabigny.plmpruebatecnica.auth.data.model.UserRegistrationResponse
import com.sabigny.plmpruebatecnica.auth.data.model.request.UserRegistrationRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RegistrationApiService {
    @GET("getCountries")
    suspend fun getCountries(): CountryResponse

    @GET("getStateByCountry")
    suspend fun getStatesByCountry(@Query("countryId") countryId: Int): StateResponse

    @GET("getProfessions")
    suspend fun getProfessions(): ProfessionResponse

    @POST("saveMobileLocationAppClient")
    suspend fun registerUser(@Body userData: UserRegistrationRequest): UserRegistrationResponse
}