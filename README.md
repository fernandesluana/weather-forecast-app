
# Weather Forecast App

A comprehensive Weather Forecast App built with Kotlin, utilizing Jetpack Compose for the UI. The app fetches weather information using the AccuWeather API and handles API requests with Retrofit, leveraging Coroutines for asynchronous operations.

## Stack

- **Kotlin** - First-class and official programming language for Android development.
- **Jetpack Compose** - Modern toolkit for building native Android UI.
- **Retrofit** - A type-safe HTTP client for Android and Java to handle API requests.
- **Coroutines** - For managing background threads and asynchronous operations.
- **AccuWeather API** - Provides weather information.

## Features

- Search weather by city
- Current weather information
- 5-day weather forecast
- 12-hour weather forecast

## Build and Run

### Prerequisites:

- Android Studio
- AccuWeather API Key

### Clone this repository:

```bash
git clone https://github.com/fernandesluana/weather-forecast-app.git
```

Or download as a zip and extract the project.

### Steps:
- Open project in Android Studio
- Add your AccuWeather API Key

Open the Api class in your project.
Locate the following line:

```bash
const val APIKEY = "Your API KEY here"
```
Replace "Your API KEY here" with your actual AccuWeather API Key.

- Wait while Android Studio Downloads gradle or required files
- Hit Run Button !
