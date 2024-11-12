package com.example.superheroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.example.superheroes.utils.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Objects

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


    }



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

        }
    }
}