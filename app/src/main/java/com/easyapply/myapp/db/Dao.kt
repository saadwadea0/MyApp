package com.easyapply.myapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.easyapply.myapp.data.Post


@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    suspend fun getPosts(): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<Post>)

   /* @Query("SELECT * FROM post WHERE is_favorite = 1")
    fun getFavourites(): LiveData<List<Post>>

    @Query("UPDATE post SET is_favorite = 1 WHERE id = :postId")
    suspend fun addFavourite(postId: Int)

    @Query("UPDATE post SET is_favorite = 0 WHERE id = :postId")
    suspend fun removeFavourite(postId: Int)*/
}