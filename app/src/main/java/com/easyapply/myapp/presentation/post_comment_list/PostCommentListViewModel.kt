package com.easyapply.myapp.presentation.post_comment_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyapply.myapp.common.Constants
import com.easyapply.myapp.common.Resource
import com.easyapply.myapp.domain.use_case.get_post_comments.GetPostCommentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostCommentListViewModel @Inject constructor(
    private val getPostCommentUseCase: GetPostCommentsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(PostCommentListState())
    val state: State<PostCommentListState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_POST_ID)?.let {postId->
            getComments(postId = postId)
        }
    }

    private fun getComments(postId:String) {
        getPostCommentUseCase.invoke(postId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PostCommentListState(postComments = result.data?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = PostCommentListState(
                        error = result.message ?: "An Unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PostCommentListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}