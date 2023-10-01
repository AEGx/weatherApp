package com.aegx.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.aegx.weatherapp.fragments.HomeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    lateinit var tvReponse: TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentt_container, HomeFragment())
            .addToBackStack(null)
            .commit();

//        tvReponse = findViewById(R.id.fragmentt_container)

        //TODO: Call retrofit with API
//        val retrofit: Retrofit = Retrofit.Builder()
//            .baseUrl("https://api.openweathermap.org/data/2.5/weather/")
//            .build();

//        val weatherservice = retrofit.create(WeatherService::class.java);
//        val res = weatherservice.getWeatherByCity();
//
//        res.enqueue(object: Callback<String> {
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                if (response.isSuccessful) {
//                    tvReponse.text = response.body();
//                }
//            }
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show();
//            }
//
//        })

    }
}