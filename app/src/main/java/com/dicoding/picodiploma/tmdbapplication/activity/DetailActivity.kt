package com.dicoding.picodiploma.tmdbapplication.activity

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.strictmode.SqliteObjectLeakedViolation
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.dicoding.picodiploma.tmdbapplication.R
import com.dicoding.picodiploma.tmdbapplication.helper.database
import com.dicoding.picodiploma.tmdbapplication.model.Favorite
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.insert

class DetailActivity : AppCompatActivity() {

    private var isFavorite: Boolean = false

    private var id_category : String = ""
    private var id_movie : String = ""
    private var title : String = ""
    private var backdrop : String = ""
    private var overview : String = ""
    private var name_category : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        id_movie = intent.getStringExtra("idMovie")
        title = intent.getStringExtra("titleMovie")
        backdrop = intent.getStringExtra("backdrop")
        overview = intent.getStringExtra("detailMovie")

        val ivDetail = findViewById<ImageView>(R.id.iv_detailitem)

        Log.e("cek overview",overview)
        Log.e("cek backdrop",backdrop)
        Log.e("cek title",title)

        tv_desc_detail.setText(overview)
        tv_title_detailIteam.setText(title)
        Picasso.get().load(backdrop).into(ivDetail)
        btn_kategori.setText("MOVIE")
        btn_add_to_favorite.setOnClickListener{
            addToFavorite()

        }

    }

    private fun addToFavorite(){
        try {
            database.use{
                insert(Favorite.TABLE_FAVORITE,
                    Favorite.MOVIE_ID to id_movie,
                    Favorite.NAME to title,
                    Favorite.POSTER to "https://image.tmdb.org/t/p/original"+backdrop)
            }
            Toast.makeText(applicationContext, "Add To Favorite is Succes", Toast.LENGTH_SHORT).show()
        }catch (e:SQLiteConstraintException){
            Toast.makeText(applicationContext, "Add To Favorite is Failed", Toast.LENGTH_SHORT).show()
        }
    }

}
