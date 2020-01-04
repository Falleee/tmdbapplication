package com.dicoding.picodiploma.tmdbapplication.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.dicoding.picodiploma.tmdbapplication.R
import com.dicoding.picodiploma.tmdbapplication.adapter.MovieAdapter
import com.dicoding.picodiploma.tmdbapplication.api.ApiRepository
import com.dicoding.picodiploma.tmdbapplication.model.Movie
import com.dicoding.picodiploma.tmdbapplication.presenter.MoviePresenter
import com.dicoding.picodiploma.tmdbapplication.view.MovieView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_now_playing.*
import org.jetbrains.anko.startActivity

class SearchActivity : AppCompatActivity(), MovieView {

    private var movies : MutableList<Movie> = mutableListOf()
    private lateinit var presenter : MoviePresenter
    private lateinit var adapter: MovieAdapter

    private lateinit var item: Movie
    private var keyword : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        keyword = intent.getStringExtra("keyword")

        val request = ApiRepository()
        val gson = Gson()
        presenter = MoviePresenter(this, request, gson)
        presenter.getSearchMovie(keyword)

        adapter = MovieAdapter(movies){
            startActivity<DetailActivity>(
                "idMovie" to it.id.toString(),
                "titleMovie" to it.judulFilm,
                "backdrop" to it.backdropFilm,
                "detailMovie" to it.detailMovie
            )
        }
        rv_search.layoutManager = GridLayoutManager(applicationContext,2) as RecyclerView.LayoutManager?
        rv_search.adapter = adapter
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
