package com.demir.news.view.view.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.demir.news.view.view.model.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(Converters::class)
abstract class ArticleDataBase :RoomDatabase(){
    abstract fun getArticleDao():ArticleDao
    companion object{
        @Volatile private var instance: ArticleDataBase?=null
        private val lock=Any()
        operator fun invoke(context: Context)= instance?: synchronized(lock){
            instance?: makeDataBase(context).also {
                instance=it
            }
        }
        private fun makeDataBase(context: Context)= Room.databaseBuilder(
            context.applicationContext,ArticleDataBase::class.java,"countrydatabase").build()


    }

}