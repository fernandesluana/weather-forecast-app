package com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.repository

import com.luanafernandes.weather_app_compose.core.util.Resource
import com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.model.location.Location


import com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.model.weather.DailyForecasts
import com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.model.weather.HourlyForecast


interface WeatherRepository {

    suspend fun getLocation(query: String): Resource<List<Location>>
    suspend fun getDailyForecast(locationKey:String):Resource<DailyForecasts>
    suspend fun getHourlyForecast(locationKey: String):Resource<List<HourlyForecast>>
}