package com.easyapply.myapp.data

import com.easyapply.myapp.data.remote.dto.PostCommentsDto
import com.easyapply.myapp.data.remote.dto.PostDetailDto
import com.easyapply.myapp.data.remote.dto.PostDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {
    @GET("posts")
    suspend fun getPosts(): List<PostDto>

    @GET("posts/{post_id}/comments")
    suspend fun getPostCommentsById(@Path("post_id") post_id:String):List<PostCommentsDto>

    @GET("posts/{post_id}")
    suspend fun getPostDetailById(@Path("post_id") post_id:String):PostDetailDto
}