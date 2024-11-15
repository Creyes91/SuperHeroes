package com.example.superheroes.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import com.example.superheroes.data.SuperHero
import com.example.superheroes.databinding.ActivityDetailBinding
import com.example.superheroes.utils.RetrofitProvider
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    companion object{

        const val SuperHero_ID= "SUPERHERO_ID"
    }

    lateinit var binding: ActivityDetailBinding
    lateinit var superHero: SuperHero
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id= intent.getStringExtra(SuperHero_ID)!!

        getSuperHero(id)


    }

    fun loadData() {
        supportActionBar?.title= superHero.name
        Picasso.get().load(superHero.image.url).into(binding.HeroImageViewDetail)

    }

    private fun getSuperHero(id: String) {
        val service= RetrofitProvider.GetRetrofit()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                superHero = service.findSuperheroesById(id)
                CoroutineScope(Dispatchers.Main).launch {
                    loadData()
                }
            }catch (e:Exception){
                Log.e("API", e.stackTraceToString())
            }

        }

    }


}