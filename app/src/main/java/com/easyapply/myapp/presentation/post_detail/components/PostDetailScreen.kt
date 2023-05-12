package com.easyapply.myapp.presentation.post_detail.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.easyapply.myapp.presentation.post_detail.PostDetailViewModel

@Composable
fun PostDetailScreen(
    viewModel: PostDetailViewModel= hiltViewModel()
) {
    val state= viewModel.state.value



}
