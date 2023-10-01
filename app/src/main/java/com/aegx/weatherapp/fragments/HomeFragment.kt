package com.aegx.weatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.aegx.weatherapp.R
import com.aegx.weatherapp.WeatherService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment: Fragment() {

    lateinit var weatherCity: TextView;
    lateinit var weatherDesc: TextView;
    lateinit var  weatherIcon: ImageView;
    lateinit var weatherTemp: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false);

        weatherCity = view.findViewById(R.id.data_city);
        weatherDesc = view.findViewById(R.id.sub_title);
        weatherIcon = view.findViewById(R.id.icon_weather);
        weatherTemp = view.findViewById(R.id.data_temp_weather);

        /* TODO: Call retrofit with API */

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/weather/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
//
        val ws = retrofit.create(WeatherService::class.java);
        val res = ws.getWeatherByCity();
//
        res.enqueue(object: Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    val result = response.body();
                    val temp = result?.get("main")?.asJsonObject?.get("temp")?.asDouble
                    val city = result?.get("name")?.asString

                    weatherTemp.text = "$temp ºC";
                    weatherCity.text = "$city"

                }
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }

        })
        return view;
    }
}