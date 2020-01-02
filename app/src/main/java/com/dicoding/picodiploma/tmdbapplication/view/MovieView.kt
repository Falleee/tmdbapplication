package com.dicoding.picodiploma.tmdbapplication.view

import com.dicoding.picodiploma.tmdbapplication.model.Movie

interface MovieView{
    fun showLoading()
    fun hideLoading()
    fun showMovie(data: List<Movie>)
}