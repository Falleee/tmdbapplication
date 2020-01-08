package com.dicoding.picodiploma.tmdbapplication.view

import com.dicoding.picodiploma.tmdbapplication.model.Movie
import com.dicoding.picodiploma.tmdbapplication.model.Trailer

interface TrailerView{
    fun showMovie(data: List<Trailer>)
}