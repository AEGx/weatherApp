package com.aegx.weatherapp

data class WeatherInterface(
    var name: String,
    var weather: Array<Weathertype>,
    var main: MainJson
)

data class MainJson(
    var temp: Double,
    var pressure: Double,
    var humidity: Double
)

data class Weathertype(
    var id: Int,
    var main: String,
    var description: String,
    var icon: String
)