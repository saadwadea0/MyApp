package com.easyapply.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.easyapply.myapp.presentation.Screen
import com.easyapply.myapp.presentation.favourite_list.FavouritePostListScreen
import com.easyapply.myapp.presentation.post_detail.components.PostDetailScreen
import com.easyapply.myapp.presentation.post_list.PostListScreen
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
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEachIndexed { index, item ->
                    val isSelected = selectedIndex == index
                    BottomNavigationItem(
                        selected = isSelected && currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            selectedIndex = index
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier,
            navController = navController,
            startDestination = Screen.PostListScreen.route
        ) {
            composable(Screen.PostDetailScreen.route) {
                PostDetailScreen()
            }
            composable(Screen.PostListScreen.route) {
                PostListScreen(
                    navController = navController
                ) { id: String ->
                    navController.navigate(
                        "post_detail_screen/$id"
                    )
                }
            }
            composable(Screen.PostFavouritesScreen.route) {
                FavouritePostListScreen(navController)
            }
        }
    }
}
data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)