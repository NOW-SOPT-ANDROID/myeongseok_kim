package com.sopt.now.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.now.BuildConfig
import com.sopt.now.data.api.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val CONTENT_TYPE = "application/json"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.AUTH_BASE_URL)
            .addConverterFactory(Json.asConverterFactory(CONTENT_TYPE.toMediaType()))
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }
}
