package com.dicoding.picodiploma.tmdbapplication.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.picodiploma.tmdbapplication.R
import com.dicoding.picodiploma.tmdbapplication.model.Favorite
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.foryou_item.view.*

class FavoriteAdapter (private val favorite:List<Favorite>, private val listener : (Favorite)->(Unit))
    :RecyclerView.Adapter<FavoriteViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.favorite_item,p0,false))
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(p0: FavoriteViewHolder, p1: Int) {
        p0.bindItem(favorite[p1],listener)

    }

}

class FavoriteViewHolder(view:View):RecyclerView.ViewHolder(view) {

    fun bindItem(item : Favorite, listener: (Favorite) -> Unit){
        itemView.tv_foryou.text = item.name
        Picasso.get().load("https://image.tmdb.org/t/p/original"+item.poster).resize(175,245).into(itemView.iv_foryou)
        itemView.setOnClickListener {
            listener(item)
        }

    }

}
