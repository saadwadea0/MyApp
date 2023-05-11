package com.easyapply.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.easyapply.myapp.BottomNavigation
import com.easyapply.myapp.presentation.FavouritesScreen
import com.easyapply.myapp.presentation.PostScreen
import com.easyapply.myapp.ui.theme.MyAppTheme
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
               /* NavHost(navController = navController, startDestination = "post") {
                    composable("post") { PostScreen(navController) }
                    composable("favourites") { FavouritesScreen(navController) }
                }*/
            }
        }
    }
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Post", Icons.Default.ShoppingCart, "post"),
        BottomNavItem("Favourites", Icons.Default.Favorite, "favourites")
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
            0 -> PostScreen(navController)
            1 -> FavouritesScreen(navController)
        }
    }
}
data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)