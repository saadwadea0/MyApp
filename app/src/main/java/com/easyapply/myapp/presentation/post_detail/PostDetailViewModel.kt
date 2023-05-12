package com.easyapply.myapp.presentation.post_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.easyapply.myapp.common.Constants
import com.easyapply.myapp.common.Resource
import com.easyapply.myapp.domain.use_case.get_post.GetPostUseCase
import com.easyapply.myapp.domain.use_case.get_post_comments.GetPostCommentsUseCase
import com.easyapply.myapp.presentation.post_comment_list.PostCommentListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val getPostDetailUseCase: GetPostUseCase,
    private val getPostCommentUseCase: GetPostCommentsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(PostDetailState())
    val state: State<PostDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_POST_ID)?.let {postId->
            getDetailPost(postId = postId)
        }
    }

    private fun getDetailPost(postId:String) {
        getPostDetailUseCase.invoke(postId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PostDetailState(post = result.data)
                }
                is Resource.Error -> {
                    _state.value = PostDetailState(
                        error = result.message ?: "An Unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PostDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    private val _commentsState = mutableStateOf(PostCommentListState())
    val commentsState: State<PostCommentListState> = _commentsState

    init {
        savedStateHandle.get<String>(Constants.PARAM_POST_ID)?.let {postId->
            getComments(postId = postId)
        }
    }

    private fun getComments(postId:String) {
        getPostCommentUseCase.invoke(postId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _commentsState.value = PostCommentListState(postComments = result.data?: emptyList())
                }
                is Resource.Error -> {
                    _commentsState.value = PostCommentListState(
                        error = result.message ?: "An Unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _commentsState.value = PostCommentListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}