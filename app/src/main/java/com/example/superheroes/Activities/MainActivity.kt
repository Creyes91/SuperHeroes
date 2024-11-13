package com.example.superheroes.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.superheroes.Adapters.SuperheroAdapter
import com.example.superheroes.R
import com.example.superheroes.data.SuperHero
import com.example.superheroes.databinding.ActivityMainBinding

import com.example.superheroes.utils.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: SuperheroAdapter
    var superHeroList: List<SuperHero> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter= SuperheroAdapter(superHeroList) {position->

            val superHero= superHeroList[position]
           // navigateTo(superHero)

        }
        binding.mainReciclerView.adapter= adapter
        binding.mainReciclerView.layoutManager= GridLayoutManager(this,2)

        searchSuperHeroes("")



    }

  /*  private fun navigateTo(superHero: SuperHero)
    {
        val intent = Intent

    }*/


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu_main, menu)

        val btnSearch = menu?.findItem(R.id.search_bar)!!

        val searchView = btnSearch.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchSuperHeroes(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }


        })

        return true
    }

    private fun searchSuperHeroes(query: String?) {
        val service= RetrofitProvider.GetRetrofit()

        CoroutineScope(Dispatchers.IO).launch {
            val result = service.findSuperheroesByName(query!!)
            println(result)

            CoroutineScope(Dispatchers.Main).launch {
                if (result.response== "success")
                    adapter.updatesItems(result.results)



            }

        }
    }
}