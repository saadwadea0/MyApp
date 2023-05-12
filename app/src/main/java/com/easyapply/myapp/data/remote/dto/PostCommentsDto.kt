package com.easyapply.myapp.data.remote.dto

import com.easyapply.myapp.domain.model.PostComments

data class PostCommentsDto(
    val body: String,
    val email: String,
    val id: String,
    val name: String,
    val postId: String
)
fun PostCommentsDto.toPostComments():PostComments{
    return PostComments(
        body = body,
        name=name
    )
}