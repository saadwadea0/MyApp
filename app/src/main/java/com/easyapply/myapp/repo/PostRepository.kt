package com.easyapply.myapp.repo

import com.easyapply.myapp.data.ApiService
import com.easyapply.myapp.data.Post
import com.easyapply.myapp.db.PostDao
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val apiService: ApiService,
    private val postDao: PostDao,
) {
    suspend fun getPosts(): List<Post> {
        val posts = postDao.getPosts()
        return posts.ifEmpty {
            val newPosts = apiService.getPosts()
            postDao.insertPosts(newPosts)
            newPosts
        }
    }

   /* fun getFavourites(): LiveData<List<Post>> {
        return postDao.getFavourites()
    }

    suspend fun addFavourite(post: Post) {
        postDao.addFavourite(post)
    }

    suspend fun removeFavourite(post: Post) {
        postDao.removeFavourite(post)
    }*/
}