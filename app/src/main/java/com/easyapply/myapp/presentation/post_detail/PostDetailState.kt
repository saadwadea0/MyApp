package com.easyapply.myapp.presentation.post_detail

import com.easyapply.myapp.domain.model.PostDetail

data class PostDetailState(
    val isLoading: Boolean = false, val post: PostDetail? = null, val error: String = ""

)
