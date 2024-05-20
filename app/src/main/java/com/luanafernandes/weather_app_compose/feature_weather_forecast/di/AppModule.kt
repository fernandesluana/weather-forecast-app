package com.luanafernandes.weather_app_compose.feature_weather_forecast.di

import com.luanafernandes.weather_app_compose.feature_weather_forecast.data.remote.HeaderInterceptor
import com.luanafernandes.weather_app_compose.feature_weather_forecast.data.remote.Api
import com.luanafernandes.weather_app_compose.feature_weather_forecast.data.repository.WeatherRepositoryImpl
import com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideLocationRepository(
        api: Api
    ): WeatherRepository {
        return WeatherRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideLocationApi(client: OkHttpClient): Api {
        return Api.create(client)
    }
}