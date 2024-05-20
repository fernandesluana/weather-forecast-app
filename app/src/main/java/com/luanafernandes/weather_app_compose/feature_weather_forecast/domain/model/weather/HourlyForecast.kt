package com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.model.weather

import com.google.gson.annotations.SerializedName
import com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.model.location.Value

data class HourlyForecast(
    @SerializedName("Date")
    val date:String,
    @SerializedName("EpochDateTime")
    val epochDateTime:Long,
    @SerializedName("WeatherIcon")
    val weatherIcon:Int,
    @SerializedName("IconPhrase")
    val iconPhrase:String,
    @SerializedName("HasPrecipitation")
    val hasPrecipitation:Boolean,
    @SerializedName("IsDaylight")
    val isDaylight:Boolean,
    @SerializedName("Temperature")
    val temperature: Value
)