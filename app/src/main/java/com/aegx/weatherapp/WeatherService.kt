package com.aegx.weatherapp

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface WeatherService {
    @GET("?q=Lile&appid=9f90901eea0dcdcc37b6d456d12a5f5a&units=matric")
    fun getWeatherByCity(): Call<JsonObject>
}