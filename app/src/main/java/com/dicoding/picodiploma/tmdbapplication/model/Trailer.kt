package com.dicoding.picodiploma.tmdbapplication.model

import com.google.gson.annotations.SerializedName

data class Trailer(
    @SerializedName("key")
    var keyTrailer : String? = ""
)