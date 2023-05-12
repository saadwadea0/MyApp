package com.easyapply.myapp.presentation.favourite_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.easyapply.myapp.presentation.Screen
import com.easyapply.myapp.presentation.favourite_list.component.PostItem
import com.easyapply.myapp.presentation.post_list.PostListViewModel

@Composable
fun PostListScreen(navController: NavController, viewModel: PostListViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    Column {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }else{
            LazyColumn {
                items(state.posts) { post ->
                    PostItem(post = post, onClick = {
                        navController.navigate(Screen.PostDetailScreen.route + "/${post.id}")
                    })
                }
            }
        }


    }
}