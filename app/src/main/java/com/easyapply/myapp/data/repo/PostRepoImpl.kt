package com.easyapply.myapp.data.repo

import com.easyapply.myapp.data.PostApi
import com.easyapply.myapp.data.remote.dto.PostCommentsDto
import com.easyapply.myapp.data.remote.dto.PostDetailDto
import com.easyapply.myapp.data.remote.dto.PostDto
import com.easyapply.myapp.db.PostDao
import com.easyapply.myapp.domain.repo.PostRepo
import javax.inject.Inject

class PostRepoImpl @Inject constructor(
    private val api:PostApi,
    private val dao: PostDao
):PostRepo {
    override suspend fun getPosts(): List<PostDto> {
        return api.getPosts().ifEmpty {
            val newPosts = api.getPosts()
            dao.insertPosts(newPosts)
            newPosts
        }
    }

    override suspend fun getPostCommentsById(postId: String): List<PostCommentsDto> {
        return api.getPostCommentsById(post_id = postId)
    }

    override suspend fun getPostDetailById(postId: String): PostDetailDto {
        return api.getPostDetailById(post_id = postId)
    }
}