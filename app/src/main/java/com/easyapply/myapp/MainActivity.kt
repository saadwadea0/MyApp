package com.easyapply.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.easyapply.myapp.presentation.FavouritesScreen
import com.easyapply.myapp.presentation.Screen
import com.easyapply.myapp.presentation.favourite_list.PostListScreen
import com.easyapply.myapp.presentation.ui.theme.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                BottomNavigation(navController = navController)

            }
        }
    }
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Post", Icons.Default.ShoppingCart, Screen.PostListScreen.route),
        BottomNavItem("Favourites", Icons.Default.Favorite, Screen.PostFavouritesScreen.route)
    )

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigation() {
                items.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        // Switch content based on selected index
        when (selectedIndex) {
            0 -> PostListScreen(navController)
            1 -> FavouritesScreen(navController)
        }
    }
}
data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)