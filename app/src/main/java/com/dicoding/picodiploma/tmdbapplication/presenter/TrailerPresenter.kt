package com.dicoding.picodiploma.tmdbapplication.presenter

import android.util.Log
import com.dicoding.picodiploma.tmdbapplication.api.ApiRepository
import com.dicoding.picodiploma.tmdbapplication.api.TMDBApi
import com.dicoding.picodiploma.tmdbapplication.model.MovieResponse
import com.dicoding.picodiploma.tmdbapplication.model.Trailer
import com.dicoding.picodiploma.tmdbapplication.model.TrailerResponse
import com.dicoding.picodiploma.tmdbapplication.view.MovieView
import com.dicoding.picodiploma.tmdbapplication.view.TrailerView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TrailerPresenter (
    private val view : TrailerView,
    private val apiRepository: ApiRepository,
    private val gson: Gson){


    fun getTrailerVideo(key:String){
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TMDBApi.getTrailer(key)),
                TrailerResponse::class.java
            )

            Log.e("cek data",data.toString())

            uiThread {
                view.showMovie(data.results)
            }

        }
    }
}