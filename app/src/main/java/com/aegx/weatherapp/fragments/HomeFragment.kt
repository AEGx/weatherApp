package com.aegx.weatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.aegx.weatherapp.R
import com.aegx.weatherapp.WeatherInterface
import com.aegx.weatherapp.WeatherService
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
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
    lateinit var inputSearch: EditText
    lateinit var btnSubmit:Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false);

        inputSearch = view.findViewById(R.id.textInputEditText);
        btnSubmit = view.findViewById(R.id.submit_btn);

        weatherCity = view.findViewById(R.id.data_city);
        weatherDesc = view.findViewById(R.id.sub_title);
        weatherIcon = view.findViewById(R.id.icon_weather);
        weatherTemp = view.findViewById(R.id.data_temp_weather);

        btnSubmit.setOnClickListener {
            val city = inputSearch.text.toString();

            if (city.isEmpty()) {
                Toast.makeText(context, "Input your city", Toast.LENGTH_SHORT).show()
            } else {
                getWeatherByCity(city)
            }
        }
        getWeatherByCity("Paris");
        return view;
    }

    private fun getWeatherByCity(city: String) {
        /* TODO: Call retrofit with API */
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/weather/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        val ws = retrofit.create(WeatherService::class.java);
        val res = ws.getWeatherByCity(city);

        res.enqueue(object: Callback<WeatherInterface> {
            override fun onResponse(call: Call<WeatherInterface>, response: Response<WeatherInterface>) {
                if (response.isSuccessful) {
                    val result = response.body();
                    Picasso.get().load("https://openweathermap.org/img/w/${result?.weather?.get(0)?.icon}.png").into(weatherIcon);
                    weatherCity.text = result?.name;
                    weatherTemp.text = "${result?.main?.temp} ºC"
                    weatherDesc.text = "${result?.weather?.get(0)?.description}"

                }
            }
            override fun onFailure(call: Call<WeatherInterface>, t: Throwable) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }

        })
    }
}