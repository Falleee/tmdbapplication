package com.dicoding.picodiploma.tmdbapplication.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.dicoding.picodiploma.tmdbapplication.R
import com.dicoding.picodiploma.tmdbapplication.adapter.FavoriteAdapter
import com.dicoding.picodiploma.tmdbapplication.helper.database
import com.dicoding.picodiploma.tmdbapplication.model.Favorite
import kotlinx.android.synthetic.main.activity_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

class FavoriteActivity : AppCompatActivity() {

    private var favorites : MutableList<Favorite> = mutableListOf()
    private lateinit var adapter : FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        adapter = FavoriteAdapter(favorites){
            applicationContext?.startActivity<DetailActivity>(
                "idMovie" to it.movieId,
                "titleMovie" to it.name,
                "backdrop" to it.poster,
                "detailMovie" to it.overview
            )
        }

        showFavorite()

        rv_favorite.layoutManager = GridLayoutManager(applicationContext,2)
        rv_favorite.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite(){
        favorites.clear()
        applicationContext?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            Log.e("cek value db",favorite.toString())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }
}
