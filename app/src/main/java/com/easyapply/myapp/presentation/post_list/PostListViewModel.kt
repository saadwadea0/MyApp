package com.easyapply.myapp.presentation.post_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyapply.myapp.common.Resource
import com.easyapply.myapp.domain.use_case.get_posts.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(PostListState())
    val state: State<PostListState> = _state

    init {
        getPosts()
    }

    private fun getPosts() {
        getPostsUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PostListState(posts = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = PostListState(
                        error = result.message ?: "An Unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PostListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}