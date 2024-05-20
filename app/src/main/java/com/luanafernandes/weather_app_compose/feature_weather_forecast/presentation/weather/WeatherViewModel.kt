package com.luanafernandes.weather_app_compose.feature_weather_forecast.presentation.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luanafernandes.weather_app_compose.core.util.Resource
import com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.model.weather.DailyForecasts
import com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.model.weather.HourlyForecast
import com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.repository.WeatherRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject



    @HiltViewModel
    class WeatherViewModel @Inject constructor(
        private val repository: WeatherRepository
    ) : ViewModel() {


        private val _hourlyForecast:MutableStateFlow<Resource<List<HourlyForecast>>> = MutableStateFlow(Resource.Loading)
        val hourlyForecast = _hourlyForecast.asStateFlow()

        private val _dailyForecast:MutableStateFlow<Resource<DailyForecasts>> = MutableStateFlow(Resource.Loading)
        val dailyForecast = _dailyForecast.asStateFlow()

        fun getHourlyForecast(locationKey:String){
            viewModelScope.launch {
                repository.getHourlyForecast(locationKey).also { data->
                    _hourlyForecast.update { data }
                }
            }
        }
        fun getDailyForecast(locationKey:String){
            viewModelScope.launch {
                repository.getDailyForecast(locationKey).also { data->
                    _dailyForecast.update { data }
                }
            }
        }
    }
