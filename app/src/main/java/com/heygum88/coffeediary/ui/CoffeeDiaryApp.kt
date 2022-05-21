package com.heygum88.coffeediary.ui


import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


const val TAG = "CoffeeDiaryApp"

/**
 * The main UI page
 */
@Composable
fun CoffeeDiaryApp() {
    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        NavAction(navController)
    }
    BottomBar(navController = navController, navAction = navigationActions)
}

/**
 * Bottom Bar of the app
 */
@Composable
fun BottomBar(navController: NavHostController,navAction: NavAction) {

    val items = listOf(
        "home",
        "coffee"
    )
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { item ->
                    BottomNavigationItem(
                        icon = {Icon(Icons.Filled.Favorite,contentDescription = null)},
                        label = { Text(item) },
                        selected = currentDestination?.hierarchy?.any { it.route == item } == true,
                        onClick = {
                            Log.d(TAG,item)
                            navAction.navigate(item).invoke()
                        })
                }
            }
        }) {
        Box(modifier = Modifier.padding(it)){
            NavGraph(navController,navAction)
        }
    }
}
