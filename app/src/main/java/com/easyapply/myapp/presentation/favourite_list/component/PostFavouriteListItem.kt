package com.easyapply.myapp.presentation.favourite_list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easyapply.myapp.domain.model.Post

@Composable
fun PostFavouriteItem(post: Post, onClick: (Post) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = { onClick(post) }),
        elevation = 4.dp
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = post.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = post.body, fontSize = 18.sp)
        }
    }
}