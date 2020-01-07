package com.dicoding.picodiploma.tmdbapplication.model

data class Favorite (val id: Long?, val movieId: String?,val name : String?, val poster: String?, val overview : String?){

    companion object{
        const val TABLE_FAVORITE : String = "TABLE_FAVORITE"
        const val ID : String = "ID_"
        const val MOVIE_ID : String = "MOVIE_ID"
        const val NAME : String = "NAME"
        const val POSTER : String = "POSTER"
        const val OVERVIEW : String = "OVERVIEW"

    }

}