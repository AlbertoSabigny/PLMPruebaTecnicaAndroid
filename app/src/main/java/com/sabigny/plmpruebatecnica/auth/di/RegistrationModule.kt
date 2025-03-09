package com.sabigny.plmpruebatecnica.auth.di

import com.sabigny.plmpruebatecnica.auth.data.matcher.EmailMatcherImpl
import com.sabigny.plmpruebatecnica.auth.data.remote.RegistrationApiService
import com.sabigny.plmpruebatecnica.auth.data.repository.RegistrationRepositoryImpl
import com.sabigny.plmpruebatecnica.auth.domain.matcher.EmailMatcher
import com.sabigny.plmpruebatecnica.auth.domain.repository.RegistrationRepository
import com.sabigny.plmpruebatecnica.auth.domain.usecases.GetCountriesUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.GetProfessionsUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.GetStatesByCountryUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.RegisterUserUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.RegisterValidationUseCases
import com.sabigny.plmpruebatecnica.auth.domain.usecases.validation.ValidateCountryUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.validation.ValidateEmailUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.validation.ValidatePhoneUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.validation.ValidateProfessionUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.validation.ValidateProfessionalIdUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.validation.ValidateStateUseCase
import com.sabigny.plmpruebatecnica.auth.domain.usecases.validation.ValidateTextOnlyUseCase
import com.sabigny.plmpruebatecnica.core.di.RegistrationRetrofit
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
object RegistrationModule {

    private const val BASE_URL = "https://dev.plmconnection.com/plmservices/RestPLMClientsEngine/RestPLMClientsEngine.svc/"

    @Provides
    @Singleton
    @RegistrationRetrofit
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRegistrationApiService(@RegistrationRetrofit retrofit: Retrofit): RegistrationApiService {
        return retrofit.create(RegistrationApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRegistrationRepository(apiService: RegistrationApiService): RegistrationRepository {
        return RegistrationRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideGetCountriesUseCase(repository: RegistrationRepository): GetCountriesUseCase {
        return GetCountriesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetStatesByCountryUseCase(repository: RegistrationRepository): GetStatesByCountryUseCase {
        return GetStatesByCountryUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetProfessionsUseCase(repository: RegistrationRepository): GetProfessionsUseCase {
        return GetProfessionsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRegisterUserUseCase(repository: RegistrationRepository): RegisterUserUseCase {
        return RegisterUserUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideEmailMatcher(): EmailMatcher {
        return EmailMatcherImpl()
    }

    @Provides
    @Singleton
    fun provideRegisterValidationUseCases(
        emailMatcher: EmailMatcher
    ): RegisterValidationUseCases {
        return RegisterValidationUseCases(
            validateEmailUseCase = ValidateEmailUseCase(emailMatcher),
            validateTextOnlyUseCase = ValidateTextOnlyUseCase(),
            validatePhoneUseCase = ValidatePhoneUseCase(),
            validateProfessionUseCase = ValidateProfessionUseCase(),
            validateProfessionalIdUseCase = ValidateProfessionalIdUseCase(),
            validateCountryUseCase = ValidateCountryUseCase(),
            validateStateUseCase = ValidateStateUseCase()
        )
    }
}
