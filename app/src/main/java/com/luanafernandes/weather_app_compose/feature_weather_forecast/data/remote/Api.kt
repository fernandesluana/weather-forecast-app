package com.luanafernandes.weather_app_compose.feature_weather_forecast.data.remote


import com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.model.location.Location
import com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.model.weather.DailyForecasts
import com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.model.weather.HourlyForecast
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface Api {

    @GET("locations/v1/cities/search")
    suspend fun searchLocation(
        @Query("apikey") apiKey: String = APIKEY,
        @Query("q") query: String
    ): List<Location>

    @GET("forecasts/v1/daily/5day/{location_key}")
    suspend fun getDailyForecasts(
        @Path("location_key") locationKey: String,
        @Query("apikey") apiKey: String = APIKEY,
        @Query("metric") metric: Boolean = true
    ): DailyForecasts

    @GET("forecasts/v1/hourly/12hour/{location_key}")
    suspend fun getHourlyForecasts(
        @Path("location_key") locationKey: String,
        @Query("apikey") apiKey: String = APIKEY,
        @Query("metric") metric: Boolean = true
    ): List<HourlyForecast>

    companion object{
        const val APIKEY = "Your API KEY here"
        private const val BASE_URL = "https://dataservice.accuweather.com/"

        fun create(client: OkHttpClient): Api {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(Api::class.java)
        }
    }
}