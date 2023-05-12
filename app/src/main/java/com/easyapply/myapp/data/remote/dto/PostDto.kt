package com.easyapply.myapp.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.easyapply.myapp.domain.model.Post

@Entity(tableName = "post")
data class PostDto(
    val body: String,
    @PrimaryKey val id: String,
    val title: String,
    val userId: String
)


fun PostDto.toPost():Post{
    return Post(
        body = body,
        id = id,
        title = title,
    )
}