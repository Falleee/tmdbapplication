package com.dicoding.picodiploma.tmdbapplication.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.dicoding.picodiploma.tmdbapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var id_category : String = ""
    private var id_movie : String = ""
    private var title : String = ""
    private var backdrop : String = ""
    private var overview : String = ""
    private var name_category : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        id_category = intent.getStringExtra("idCategory")
        id_movie = intent.getStringExtra("idMovie")
        title = intent.getStringExtra("titleMovie")
        backdrop = intent.getStringExtra("backdrop")
        overview = intent.getStringExtra("detailMovie")
        name_category = intent.getStringExtra("nameCategory")

        val ivDetail = findViewById<ImageView>(R.id.iv_detailitem)

        Log.e("cek overview",overview)
        Log.e("cek backdrop",backdrop)
        Log.e("cek title",title)

        tv_desc_detail.setText(overview)
        tv_title_detailIteam.setText(title)
        Picasso.get().load("https://image.tmdb.org/t/p/original"+backdrop).into(ivDetail)
        btn_kategori.setText(name_category)

    }

}
