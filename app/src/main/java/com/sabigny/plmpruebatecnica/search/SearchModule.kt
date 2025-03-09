package com.sabigny.plmpruebatecnica.search

import com.sabigny.plmpruebatecnica.core.data.local.DataStoreManager
import com.sabigny.plmpruebatecnica.core.di.SearchRetrofit
import com.sabigny.plmpruebatecnica.core.usecases.GetUserCodeUseCase
import com.sabigny.plmpruebatecnica.search.data.remote.DrugApiService
import com.sabigny.plmpruebatecnica.search.data.repository.SearchRepositoryImpl
import com.sabigny.plmpruebatecnica.search.domain.repository.SearchRepository
import com.sabigny.plmpruebatecnica.search.domain.usescases.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Provides
    @Singleton
    @SearchRetrofit
    fun provideSearchRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dev.plmconnection.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSearchApiService(@SearchRetrofit retrofit: Retrofit): DrugApiService {
        return retrofit.create(DrugApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(apiService: DrugApiService): SearchRepository {
        return SearchRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideSearchUseCase(repository: SearchRepository): SearchUseCase {
        return SearchUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetUserCodeUseCase(dataStoreManager: DataStoreManager): GetUserCodeUseCase {
        return GetUserCodeUseCase(dataStoreManager)
    }
}
