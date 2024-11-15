package com.example.superheroes.services

import com.example.superheroes.data.SuperHero
import com.example.superheroes.data.SuperHeroeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


// Interface para hacer la llamada con Retrofit
interface SuperHeroeService {

    @GET("search/{name}") suspend fun findSuperheroesByName(@Path("name") query: String): SuperHeroeResponse
    @GET("{character-id}") suspend fun findSuperheroesById (@Path("character-id") query: String): SuperHero
}