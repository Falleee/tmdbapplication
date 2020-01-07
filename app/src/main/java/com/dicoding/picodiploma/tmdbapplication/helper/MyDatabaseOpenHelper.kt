package com.dicoding.picodiploma.tmdbapplication.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.dicoding.picodiploma.tmdbapplication.model.Favorite
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx : Context):ManagedSQLiteOpenHelper(ctx,"Favorite.db",null,1){

    companion object{
        private var instance : MyDatabaseOpenHelper?= null

        @Synchronized
        fun getInstance (ctx: Context):MyDatabaseOpenHelper{

            if (instance == null){
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper

        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        //buat table
        db.createTable(Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.MOVIE_ID to TEXT + UNIQUE,
            Favorite.NAME to TEXT,
            Favorite.POSTER to TEXT,
            Favorite.OVERVIEW to TEXT)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //upgrade table
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }

}

val Context.database : MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)