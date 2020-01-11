package com.dicoding.picodiploma.tmdbapplication.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.dicoding.picodiploma.tmdbapplication.R
import com.dicoding.picodiploma.tmdbapplication.adapter.MovieAdapter
import com.dicoding.picodiploma.tmdbapplication.api.ApiRepository
import com.dicoding.picodiploma.tmdbapplication.model.Movie
import com.dicoding.picodiploma.tmdbapplication.presenter.MoviePresenter
import com.dicoding.picodiploma.tmdbapplication.view.MovieView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_category.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity

class CategoryActivity : AppCompatActivity(), MovieView {

    private var movies : MutableList<Movie> = mutableListOf()
    private lateinit var presenter : MoviePresenter
    private lateinit var adapter: MovieAdapter

    private lateinit var item: Movie
    private var id_category : String = ""
    private var name_category : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        id_category = intent.getStringExtra("idCategory")
        name_category = intent.getStringExtra("nameCategory")

        tv_title_category.setText(name_category+" Movie")

        val request = ApiRepository()
        val gson = Gson()
        presenter = MoviePresenter(this, request, gson)
        presenter.getMovieGenre(id_category)

        adapter = MovieAdapter(movies){
            startActivity<DetailActivity>(
                "idMovie" to it.id.toString(),
                "titleMovie" to it.judulFilm,
                "backdrop" to it.backdropFilm,
                "detailMovie" to it.detailMovie

            )
            Log.e("cek id", it.id.toString())
            Log.e("cek overview", it.detailMovie.toString())

        }
        rv_category_list_movie.layoutManager = GridLayoutManager(this,2) as RecyclerView.LayoutManager?
        rv_category_list_movie.adapter = adapter

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMovie(data: List<Movie>) {

        movies.clear()
        movies.addAll(data)
        adapter.notifyDataSetChanged()

        item = Movie(
            data[0].id,
            data[0].judulFilm,
            data[0].posterFilm,
            data[0].detailMovie,
            data[0].backdropFilm
        )


    }

}
