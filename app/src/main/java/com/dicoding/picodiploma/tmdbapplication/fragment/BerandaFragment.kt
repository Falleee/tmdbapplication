package com.dicoding.picodiploma.tmdbapplication.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import com.dicoding.picodiploma.tmdbapplication.R
import com.dicoding.picodiploma.tmdbapplication.adapter.MoviViewHolder
import com.dicoding.picodiploma.tmdbapplication.adapter.MovieAdapter
import com.dicoding.picodiploma.tmdbapplication.api.ApiRepository
import com.dicoding.picodiploma.tmdbapplication.model.Movie
import com.dicoding.picodiploma.tmdbapplication.presenter.MoviePresenter
import com.dicoding.picodiploma.tmdbapplication.view.MovieView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_beranda.*

class BerandaFragment : Fragment(),MovieView {

    private var movies : MutableList<Movie> = mutableListOf()
    private lateinit var presenter : MoviePresenter
    private lateinit var adapter: MovieAdapter

    private lateinit var item:Movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MoviePresenter(this, request, gson)
        presenter.getUpComingMovie()

        adapter = MovieAdapter(movies){}
        rv_for_you.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        rv_for_you.adapter = adapter

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
            data[0].posterFilm
        )


    }



}