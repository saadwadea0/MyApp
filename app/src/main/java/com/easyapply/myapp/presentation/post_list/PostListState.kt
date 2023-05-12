package com.easyapply.myapp.presentation.post_list

import com.easyapply.myapp.domain.model.Post

data class PostListState(
    val isLoading: Boolean = false, val posts: List<Post> = emptyList(), val error: String = ""

)
