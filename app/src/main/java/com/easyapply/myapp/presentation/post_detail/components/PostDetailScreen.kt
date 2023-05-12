package com.easyapply.myapp.presentation.post_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.easyapply.myapp.presentation.post_detail.PostDetailViewModel

@Composable
fun PostDetailScreen(
    viewModel: PostDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val commentsState = viewModel.commentsState.value

    if (state.isLoading || commentsState.isLoading) {
        CircularProgressIndicator(modifier = Modifier)
    } else {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            item {
                Column(

                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        elevation = 4.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = "${state.post?.title}",
                                fontSize = 20.sp,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                                    .padding(bottom = 10.dp),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${state.post?.body}",
                                fontSize = 18.sp,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                            IconButton(
                                onClick = { },
                                modifier = Modifier.align(Alignment.End),
                                content = {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = "Like",
                                        tint = Color.Red
                                    )
                                }
                            )
                        }
                    }
                    Text(
                        text = "Comments:",
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Start)
                            .padding(top = 10.dp, bottom = 6.dp),
                        fontWeight = FontWeight.Bold
                    )
                }

            }
            items(commentsState.postComments) { comment ->
                CommentItem(comment)
            }
        }
    }
}
