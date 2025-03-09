package com.sabigny.plmpruebatecnica.search.data.remote

import com.sabigny.plmpruebatecnica.search.data.model.DrugResponse
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DrugApiService {

    @GET("plmservices/RestPLMPharmaSearchEngine/RestPharmaSearchEngine.svc/getDrugs")
    suspend fun getDrugs(
        @Query("code") code: String,
        @Query("countryId") countryId: String,
        @Query("editionId") editionId: Int,
        @Query("drug") drug: String,
        @Query("searchAddressIP") searchAddressIP: String? = "",
        @Query("searchLatitude") searchLatitude: String? = "",
        @Query("searchLongitude") searchLongitude: String? = ""
    ): List<DrugResponse>
}

