package com.example.superheroes.data

import com.google.gson.annotations.SerializedName

//clase que crea el objeto segun la llamada que se haga al API
data class SuperHeroeResponse(  //busqueda por nombre
    @SerializedName("response") val response: String,
    @SerializedName ("results-for") val resultsFor: String,
    @SerializedName("results") val results: List<SuperHero>
){}

data class SuperHero( //busqueda por id
    @SerializedName("response") val response: String,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: Image
)
{}
data class Image(
   @SerializedName("url") val url: String
){}