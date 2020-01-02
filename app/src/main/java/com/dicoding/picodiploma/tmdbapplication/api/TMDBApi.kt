package com.dicoding.picodiploma.tmdbapplication.api

import com.dicoding.picodiploma.tmdbapplication.BuildConfig

object TMDBApi {

    fun getUpComing():String{
        return BuildConfig.BASE_URL +
                "3/movie/upcoming?api_key=" +
                BuildConfig.TMDB_API_KEY +
                "&language=en-US&page=1"
    }

    fun getMovieGenre (genre: String?):String{
        return BuildConfig.BASE_URL +
                "3/discover/movie?api_key="+
                BuildConfig.TMDB_API_KEY +
                "&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&primary_release_date.gte=1990-01-01&primary_release_date.lte=1999-12-31&vote_average.gte=6&with_genres="+
                genre

    }

    fun getPopularMovie():String{
        return BuildConfig.BASE_URL +
                "3/movie/popular?api_key=" +
                BuildConfig.TMDB_API_KEY +
                "&language=en-US&page=1"

    }

    fun getNowShowing():String{
        return BuildConfig.BASE_URL +
                "3/movie/now_playing?api_key=" +
                BuildConfig.TMDB_API_KEY +
                "&language=en-US&page=1"
    }

    fun getDetailMovie(idMovie:String?):String{
        return BuildConfig.BASE_URL +
                "3/movie/"+
                idMovie+
                "?api_key=" +
                BuildConfig.TMDB_API_KEY +
                "&language=en-US"
    }

    fun getSearchMovie(query:String?):String{
        return BuildConfig.BASE_URL +
                "3/search/movie?api_key=" +
                BuildConfig.TMDB_API_KEY +
                "&language=en-US&query="+
                query+
                "&page=1"
    }

}