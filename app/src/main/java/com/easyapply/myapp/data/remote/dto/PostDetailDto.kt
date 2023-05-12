package com.easyapply.myapp.data.remote.dto

import com.easyapply.myapp.domain.model.PostDetail

data class PostDetailDto(
    val body: String,
    val id: String,
    val userId: String,
    val title: String,


)

fun PostDetailDto.toPostDetail():PostDetail{
    return PostDetail(
        title = title,
        body = body
    )
}