package com.dicoding.picodiploma.tmdbapplication.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.dicoding.picodiploma.tmdbapplication.R
import com.dicoding.picodiploma.tmdbapplication.item.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryItemAdapter (private val category : List<Category>, private val listener : (Category) -> (Unit))
    :RecyclerView.Adapter<CategoryViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.category_item,p0, false))
    }

    override fun getItemCount(): Int = category.size

    override fun onBindViewHolder(p0: CategoryViewHolder, p1: Int) {
        p0.bindItem(category[p1],listener)
    }

}

class CategoryViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val ivCategory = view.findViewById<ImageView>(R.id.iv_category)

    fun bindItem (item : Category,listener: (Category) -> Unit){

        Picasso.get().load(item.image).into(ivCategory)
        itemView.tv_category.text = item.name
        itemView.setOnClickListener{
            listener(item)
        }

    }

}
