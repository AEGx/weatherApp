package com.aegx.weatherapp

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    companion object{
        const val API_KAY = "9f90901eea0dcdcc37b6d456d12a5f5a";
    }
    @GET("?appid=${API_KAY}&units=metric")
    fun getWeatherByCity(@Query("q") city: String): Call<WeatherInterface>
}