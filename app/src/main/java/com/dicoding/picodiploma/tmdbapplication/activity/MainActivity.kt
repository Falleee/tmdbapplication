package com.dicoding.picodiploma.tmdbapplication.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.tmdbapplication.R
import com.dicoding.picodiploma.tmdbapplication.adapter.MyPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager_main.adapter = MyPagerAdapter(supportFragmentManager)
        tabs_main.setupWithViewPager(viewpager_main)
    }

}
