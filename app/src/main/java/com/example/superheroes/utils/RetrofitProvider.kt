package com.example.superheroes.utils

import com.example.superheroes.services.SuperHeroeService
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitProvider { // clase que hace la llamada

    companion object
    {
        fun GetRetrofit() : SuperHeroeService
        {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://superheroapi.com/api/83dd00e52aa36f40333fcd2f2701b496/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(SuperHeroeService::class.java)
        }



    }
}