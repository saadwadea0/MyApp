package com.easyapply.myapp.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.easyapply.myapp.data.Post
import com.easyapply.myapp.viewmodel.FavouritesViewModel

@Composable
fun FavouritesScreen(navController: NavController, viewModel: FavouritesViewModel = hiltViewModel()) {
   // val favourites by viewModel.favourites.observeAsState(emptyList())

    Column {
        LazyColumn {
           /* items(favourites) { favourite ->
             *//*   FavouriteItem(favourite = favourite, onClick = {
                    navController.navigate("post")
                })*//*
            }*/
        }
    }
}

@Composable
fun FavouriteItem(favourite: Post, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 4.dp
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = favourite.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = favourite.body, fontSize = 18.sp)
        }
    }
}