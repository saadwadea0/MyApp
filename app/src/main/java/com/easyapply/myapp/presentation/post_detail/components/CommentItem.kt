package com.easyapply.myapp.presentation.post_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.easyapply.myapp.domain.model.PostComments

@Composable
fun CommentItem(comment: PostComments) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp),
        elevation = 4.dp
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = comment.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = comment.body, fontSize = 16.sp)
        }
    }
}