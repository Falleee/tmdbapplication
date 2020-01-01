package com.dicoding.picodiploma.tmdbapplication.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import com.dicoding.picodiploma.tmdbapplication.R
import com.dicoding.picodiploma.tmdbapplication.activity.CategoryActivity
import com.dicoding.picodiploma.tmdbapplication.adapter.CategoryItemAdapter
import com.dicoding.picodiploma.tmdbapplication.adapter.MoviViewHolder
import com.dicoding.picodiploma.tmdbapplication.adapter.MovieAdapter
import com.dicoding.picodiploma.tmdbapplication.api.ApiRepository
import com.dicoding.picodiploma.tmdbapplication.item.Category
import com.dicoding.picodiploma.tmdbapplication.model.Movie
import com.dicoding.picodiploma.tmdbapplication.presenter.MoviePresenter
import com.dicoding.picodiploma.tmdbapplication.view.MovieView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_beranda.*
import org.jetbrains.anko.support.v4.startActivity

class BerandaFragment : Fragment(),MovieView {

    private var movies : MutableList<Movie> = mutableListOf()
    private val categories:MutableList<Category> = mutableListOf()
    private lateinit var presenter : MoviePresenter
    private lateinit var adapter: MovieAdapter

    private lateinit var item:Movie


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MoviePresenter(this, request, gson)
        presenter.getUpComingMovie()

        adapter = MovieAdapter(movies){}
        rv_for_you.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        rv_for_you.adapter = adapter

        initDataCategory()
        rv_category.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_category.adapter = CategoryItemAdapter(categories){
            startActivity<CategoryActivity>(
                "idCategory" to it.id
            )


        }

    }

    private fun initDataCategory() {
        val categoryId = resources.getStringArray(R.array.id_category)
        val categoryName = resources.getStringArray(R.array.name_category)
        val categoryImage = resources.obtainTypedArray(R.array.category_logo)

        categories.clear()
        for (i in categoryName.indices){
            categories.add(Category(categoryId[i]
                ,categoryName[i]
                ,categoryImage.getResourceId(i,0)
                ))
        }

        categoryImage.recycle()

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMovie(data: List<Movie>) {

        movies.clear()
        movies.addAll(data)
        adapter.notifyDataSetChanged()

        item = Movie(
            data[0].id,
            data[0].judulFilm,
            data[0].posterFilm
        )


    }



}
