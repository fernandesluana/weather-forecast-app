package com.luanafernandes.weather_app_compose.feature_weather_forecast.presentation.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luanafernandes.weather_app_compose.core.util.Resource
import com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.model.location.Location
import com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel(){

    private val _locations: MutableStateFlow<Resource<List<Location>>?> = MutableStateFlow(null)
    val locations = _locations.asStateFlow()

    fun searchLocation(query:String){
        viewModelScope.launch {
            _locations.update { Resource.Loading }
            repository.getLocation(query).also { data->
                _locations.update { data }
            }
        }
    }
}