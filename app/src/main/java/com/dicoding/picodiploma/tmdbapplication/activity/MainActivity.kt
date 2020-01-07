package com.dicoding.picodiploma.tmdbapplication.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import com.dicoding.picodiploma.tmdbapplication.R
import com.dicoding.picodiploma.tmdbapplication.adapter.MyPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import android.widget.TextView
import android.widget.Toolbar





class MainActivity : AppCompatActivity() {
    lateinit var keyword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager_main.adapter = MyPagerAdapter(supportFragmentManager)
        tabs_main.setupWithViewPager(viewpager_main)



        /*val toolbarTop : Toolbar = findViewById(R.id.toolbar)
        //val mTitle = toolbarTop.findViewById(R.id.tv_toolbar) as TextView
        setActionBar(toolbarTop.findViewById(R.id.tv_toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)*/

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val toolbarText = findViewById(R.id.tv_toolbar) as TextView
        if (toolbarText != null && toolbar != null) {
            toolbarText.text = title
            setSupportActionBar(toolbar)
        }

        fab_favorite.setOnClickListener{
            startActivity<FavoriteActivity>()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchItem = menu.findItem(R.id.id_menu_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setQueryHint("Search by Movie Name")
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                keyword = query.toString()
                //presenter.getLeague(keyword)
                startActivity<SearchActivity>(
                    "keyword" to keyword
                )

                Toast.makeText(applicationContext,keyword, Toast.LENGTH_SHORT).show()
                return false
            }

        })

        return true
    }

}
