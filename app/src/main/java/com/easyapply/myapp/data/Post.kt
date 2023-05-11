package com.easyapply.myapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class Post(

    @PrimaryKey val id: Int, val userId: Int, val title: String, val body: String
)