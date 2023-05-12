package com.easyapply.myapp.presentation.post_comment_list

import com.easyapply.myapp.domain.model.PostComments

data class PostCommentListState(
    val isLoading: Boolean = false, val postComments: List<PostComments> = emptyList(), val error: String = ""

)
