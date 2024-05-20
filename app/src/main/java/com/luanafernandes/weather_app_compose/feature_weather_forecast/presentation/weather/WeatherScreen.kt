package com.luanafernandes.weather_app_compose.feature_weather_forecast.presentation.weather

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.sharp.ArrowDownward
import androidx.compose.material.icons.sharp.ArrowUpward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.luanafernandes.weather_app_compose.core.util.Resource
import com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.model.weather.DailyForecasts
import com.luanafernandes.weather_app_compose.feature_weather_forecast.domain.model.weather.HourlyForecast
import com.luanafernandes.weather_app_compose.ui.theme.ubuntuFont

import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun WeatherScreen(
    navController: NavController,
    locationKey: String,
    locationName: String,
    country: String,
) {
    val viewModel: WeatherViewModel = hiltViewModel()

    val dailyForecasts by viewModel.dailyForecast.collectAsState()
    val hourlyForecasts by viewModel.hourlyForecast.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getDailyForecast(locationKey)
        viewModel.getHourlyForecast(locationKey)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        WeatherCard(
            locationName = locationName,
            country = country,
            hourlyForecasts = hourlyForecasts,
            navController = navController
        )
        Spacer(modifier = Modifier.height(16.dp)) // remove spacer from here
        HourlyForecastDisplay(hourlyForecasts = hourlyForecasts)
        Spacer(modifier = Modifier.height(16.dp)) // remove spacer from here
        DailyForecastDisplay(dailyForecasts = dailyForecasts)
    }
}

@Composable
fun WeatherCard(
    locationName: String,
    country: String,
    hourlyForecasts: Resource<List<HourlyForecast>>,
    navController: NavController
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ), modifier = Modifier
            .fillMaxWidth()
            .height(230.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 10.dp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.TopStart)
                    .clickable {
                        navController.popBackStack()
                    },
                tint = Color.Black,
                contentDescription = null,
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Icon(
                        imageVector = Icons.Rounded.LocationOn,
                        modifier = Modifier
                            .size(30.dp),
                        tint = Color.Black,
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = locationName,
                            color = Color.Black,
                            //fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                        Text(text = country, color = Color.Black)
                    }
                }
            }
        }
        AnimatedVisibility(visible = hourlyForecasts is Resource.Success) {
            val data = hourlyForecasts as Resource.Success
            val iconNow = data.data.first().weatherIcon
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${data.data.first().temperature.value.toInt()}°",
                    //fontWeight = FontWeight.Bold,
                    fontSize = 80.sp,
                    color = Color.Black,
                    fontFamily = ubuntuFont
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier.size(70.dp),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://developer.accuweather.com/sites/default/files/${iconNow.fixIcon()}-s.png")
                            .build(),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = data.data.first().iconPhrase,
                        fontSize = 18.sp
                    )
                }

            }
        }


    }

    AnimatedVisibility(visible = hourlyForecasts is Resource.Loading) {
        Loading()
    }
}


@Composable
fun HourlyForecastDisplay(hourlyForecasts: Resource<List<HourlyForecast>>) {
    Text(
        "Hourly Forecasts:",
        //fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = Color.White,
        modifier = Modifier.padding(horizontal = 10.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    AnimatedVisibility(visible = hourlyForecasts is Resource.Success) {
        val data = hourlyForecasts as Resource.Success
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            items(data.data) { forecast ->
                Column(
                    modifier = Modifier
                        .size(100.dp, 120.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.DarkGray),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = SimpleDateFormat("H a").format(Date(forecast.epochDateTime * 1000)),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    AsyncImage(
                        modifier = Modifier.size(70.dp),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://developer.accuweather.com/sites/default/files/${forecast.weatherIcon.fixIcon()}-s.png")
                            .build(),
                        contentScale = ContentScale.Fit,
                        contentDescription = null
                    )
                    Text(
                        text = "${forecast.temperature.value.toInt()}" + "°",
                        color = Color.White
                    )
                }
            }
        }
    }
    AnimatedVisibility(visible = hourlyForecasts is Resource.Loading) {
        Loading()
    }
}

@Composable
fun DailyForecastDisplay(
    dailyForecasts: Resource<DailyForecasts>
) {
    Text(
        "Daily Forecasts:",
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = Color.White,
        modifier = Modifier.padding(horizontal = 10.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    AnimatedVisibility(visible = dailyForecasts is Resource.Success) {
        val data = dailyForecasts as Resource.Success
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            items(data.data.dailyForecasts) { forecast ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.DarkGray)
                        .padding(start = 16.dp, end = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = SimpleDateFormat("d").format(Date(forecast.epochDate * 1000)),
                        color = Color.White
                    )
                    Row {
                        Icon(
                            Icons.Sharp.ArrowDownward,
                            tint = Color(0xFFFFFFFF),
                            contentDescription = null
                        )
                        Text(
                            text = "${forecast.temperature.min.value.toInt()}°",
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            Icons.Sharp.ArrowUpward,
                            tint = Color(0xFFFFFFFF),
                            contentDescription = null
                        )
                        Text(
                            text = "${forecast.temperature.max.value.toInt()}°",
                            color = Color.White
                        )
                    }
                    AsyncImage(
                        modifier = Modifier.size(70.dp),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://developer.accuweather.com/sites/default/files/${forecast.day.icon.fixIcon()}-s.png")
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
    AnimatedVisibility(visible = dailyForecasts is Resource.Loading) {
        Loading()
    }
}

@Composable
fun Loading() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = Color.Black)
    }
}

fun Int.fixIcon(): String {
    return if (this > 9) {
        toString()
    } else {
        "0${this}"
    }
}
