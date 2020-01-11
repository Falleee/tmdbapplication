package com.dicoding.picodiploma.tmdbapplication.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.dicoding.picodiploma.tmdbapplication.R
import com.dicoding.picodiploma.tmdbapplication.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.foryou_item.view.*

class ForYouAdapter (private val movie: List<Movie>, private val listener : (Movie)->(Unit))
    : RecyclerView.Adapter<ForYouViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ForYouViewHolder {
        return ForYouViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.foryou_item, p0, false))

    }

    override fun getItemCount(): Int = movie.size

    override fun onBindViewHolder(p0: ForYouViewHolder, p1: Int) {
        p0.bindItem(movie[p1],listener)

    }
}

class ForYouViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val ivPoster = view.findViewById<ImageView>(R.id.iv_foryou)

    fun bindItem(movies: Movie, listener: (Movie) -> Unit){

        Picasso.get().load("https://image.tmdb.org/t/p/original"+movies.posterFilm).resize(175,245).into(ivPoster)
        itemView.tv_foryou.text = movies.judulFilm
        itemView.tv_rate.text = movies.rate

        itemView.setOnClickListener{
            listener(movies)
        }

    }

}
