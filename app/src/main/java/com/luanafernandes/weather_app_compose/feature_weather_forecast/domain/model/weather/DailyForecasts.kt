package com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.model.weather

import com.google.gson.annotations.SerializedName

data class DailyForecasts(
    @SerializedName("DailyForecasts")
    val dailyForecasts: List<DailyForecast>
)