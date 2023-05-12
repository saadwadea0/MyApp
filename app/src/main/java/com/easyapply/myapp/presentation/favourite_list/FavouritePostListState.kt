package com.easyapply.myapp.presentation.favourite_list

import com.easyapply.myapp.domain.model.Post

data class FavouritePostListState(
    val isLoading: Boolean = false, val posts: List<Post> = emptyList(), val error: String = ""

)
