package com.dicoding.picodiploma.tmdbapplication.api

import java.net.URL

class ApiRepository {

    fun doRequest (url:String):String{
        return URL(url).readText()
    }
}