package com.easyapply.myapp.domain.repo

import com.easyapply.myapp.data.remote.dto.PostCommentsDto
import com.easyapply.myapp.data.remote.dto.PostDetailDto
import com.easyapply.myapp.data.remote.dto.PostDto

interface PostRepo {

    suspend fun getPosts(): List<PostDto>

    suspend fun getPostDetailById(postId:String): PostDetailDto

    suspend fun getPostCommentsById(postId:String): List<PostCommentsDto>
}