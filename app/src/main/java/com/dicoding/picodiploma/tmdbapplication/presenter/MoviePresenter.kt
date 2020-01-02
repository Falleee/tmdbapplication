package com.dicoding.picodiploma.tmdbapplication.presenter

import com.dicoding.picodiploma.tmdbapplication.api.ApiRepository
import com.dicoding.picodiploma.tmdbapplication.api.TMDBApi
import com.dicoding.picodiploma.tmdbapplication.model.MovieResponse
import com.dicoding.picodiploma.tmdbapplication.view.MovieView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MoviePresenter (
    private val view : MovieView,
    private val apiRepository: ApiRepository,
    private val gson: Gson){

    fun getUpComingMovie(){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TMDBApi.getUpComing()),
            MovieResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMovie(data.results)
            }

        }
    }

    fun getMovieGenre(genre: String){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TMDBApi.getMovieGenre(genre)),
                MovieResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMovie(data.results)
            }

        }
    }

    fun getPopularMovie(){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TMDBApi.getPopularMovie()),
                MovieResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMovie(data.results)
            }

        }
    }

    fun getNowShowingMovie(){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TMDBApi.getNowShowing()),
                MovieResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMovie(data.results)
            }

        }
    }



    fun getSearchMovie(query: String){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TMDBApi.getSearchMovie(query)),
                MovieResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMovie(data.results)
            }

        }
    }

}