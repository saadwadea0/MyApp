package com.easyapply.myapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.easyapply.myapp.data.Post

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}