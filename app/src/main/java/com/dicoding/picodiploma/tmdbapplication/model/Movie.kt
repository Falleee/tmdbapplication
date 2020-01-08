package com.dicoding.picodiploma.tmdbapplication.model

import com.google.gson.annotations.SerializedName

data class Movie (

    @SerializedName("id")
    var id : Int? = 0,

    @SerializedName("title")
    var judulFilm : String? = "",

    @SerializedName("poster_path")
    var posterFilm : String? = "",

    @SerializedName("overview")
    var detailMovie : String? = "",

    @SerializedName("backdrop_path")
    var backdropFilm : String? = ""


)