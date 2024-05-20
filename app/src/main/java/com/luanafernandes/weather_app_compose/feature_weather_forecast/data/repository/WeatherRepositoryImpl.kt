package com.luanafernandes.weather_app_compose.feature_weather_forecast.data.repository

import com.luanafernandes.weather_app_compose.core.util.Resource
import com.luanafernandes.weather_app_compose.feature_weather_forecast.data.remote.Api
import com.luanafernandes.weather_app_compose.feature_weather_forecast.data.remote.Api.Companion.APIKEY
import com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.model.location.Location
import com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.model.weather.DailyForecasts
import com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.model.weather.HourlyForecast
import com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.repository.WeatherRepository

import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val api: Api
) : WeatherRepository {

    override suspend fun getLocation(query: String): Resource<List<Location>> {
        return try {
            Resource.Success(
                data = api.searchLocation(query = query)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }


    override suspend fun getDailyForecast(locationKey: String): Resource<DailyForecasts> {
        return try {
                Resource.Success(
                    data = api.getDailyForecasts(locationKey = locationKey)
                )
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Error(e.message.toString())
            }
        }


    override suspend fun getHourlyForecast(locationKey: String): Resource<List<HourlyForecast>> {
        return try {
                Resource.Success(
                    data = api.getHourlyForecasts(locationKey = locationKey)
                )
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.Error(e.message.toString())
            }
        }
    }


