package com.dicoding.picodiploma.tmdbapplication.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.dicoding.picodiploma.tmdbapplication.BuildConfig
import com.dicoding.picodiploma.tmdbapplication.R
import com.dicoding.picodiploma.tmdbapplication.api.ApiRepository
import com.dicoding.picodiploma.tmdbapplication.helper.database
import com.dicoding.picodiploma.tmdbapplication.model.Favorite
import com.dicoding.picodiploma.tmdbapplication.model.Movie
import com.dicoding.picodiploma.tmdbapplication.model.Trailer
import com.dicoding.picodiploma.tmdbapplication.presenter.MoviePresenter
import com.dicoding.picodiploma.tmdbapplication.presenter.TrailerPresenter
import com.dicoding.picodiploma.tmdbapplication.view.MovieView
import com.dicoding.picodiploma.tmdbapplication.view.TrailerView
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailActivity :YouTubeBaseActivity(), TrailerView {

    private var isFavorite: Boolean = false

    private var id_category : String = ""
    private var id_movie : String = ""
    private var title : String = ""
    private var backdrop : String = ""
    private var overview : String = ""
    private var ytKey : String = ""

    private lateinit var item : Trailer

    lateinit var youtubePlayerInit : YouTubePlayer.OnInitializedListener
    private lateinit var presenter : TrailerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        favoriteState()

        setFavorite()


        id_movie = intent.getStringExtra("idMovie")
        title = intent.getStringExtra("titleMovie")
        backdrop = intent.getStringExtra("backdrop")
        overview = intent.getStringExtra("detailMovie")

        Log.e("cek id mov det",id_movie)

        val ivDetail = findViewById<ImageView>(R.id.iv_detailitem)

        tv_desc_detail.setText(overview)
        tv_title_detailIteam.setText(title)
        Picasso.get().load("https://image.tmdb.org/t/p/original"+backdrop).into(ivDetail)
        btn_kategori.setText("MOVIE")
        iv_play.visibility = View.VISIBLE

        val request = ApiRepository()
        val gson = Gson()
        Log.e("cek id mov det",id_movie)
        presenter = TrailerPresenter(this,request,gson)
        presenter.getTrailerVideo(id_movie)
        Log.e("cek id mov det",id_movie)

        btn_add_to_favorite.setOnClickListener{

            if (isFavorite) deleteFavorite() else addToFavorite()

            isFavorite = !isFavorite
            setFavorite()
            true


        }

    }

    override fun showMovie(data: List<Trailer>) {

        item = Trailer(
            data[0].keyTrailer
        )

        ytKey = data[0].keyTrailer!!

        Log.e("cek key",ytKey)

        initYt()

    }

    private fun initYt() {
        youtubePlayerInit = object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
                p1?.loadVideo(ytKey)

            }

            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Toast.makeText(applicationContext,"some thing wrong",Toast.LENGTH_SHORT).show()

            }

        }

        yt_player_view.setOnClickListener(View.OnClickListener { v->
            iv_play.visibility = View.GONE
            yt_player_view.initialize(BuildConfig.YT_API_KEY,youtubePlayerInit)

        })
    }

    private fun addToFavorite(){
        try {
            database.use{
                insert(Favorite.TABLE_FAVORITE,
                    Favorite.MOVIE_ID to id_movie,
                    Favorite.NAME to title,
                    Favorite.POSTER to backdrop,
                    Favorite.OVERVIEW to overview)
            }
            Toast.makeText(applicationContext, "Add To Favorite is Succes", Toast.LENGTH_SHORT).show()
        }catch (e:SQLiteConstraintException){
            Toast.makeText(applicationContext, "Add To Favorite is Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite(){
        if (isFavorite)
            btn_add_to_favorite.text = "Delete from Favorite"
        else
            btn_add_to_favorite.text = "Add to Favorite"

    }

    private fun deleteFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE,"(MOVIE_ID = {id_movie})",
                    "id_movie" to id_movie)
            }
            Toast.makeText(applicationContext, "Remove from Favorite is Succes", Toast.LENGTH_SHORT).show()
        }catch (e:SQLiteConstraintException){
            Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }

    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(MOVIE_ID = {id_movie})",
                    "id_movie" to id_movie)
            val favorite  =result.parseList(classParser<Favorite>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }


}
