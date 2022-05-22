package com.heygum88.diri.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.heygum88.diri.ui.coffee.CoffeeRoute
import com.heygum88.diri.ui.diary.ReadDiary
import com.heygum88.diri.ui.diary.WriteDiary
import com.heygum88.diri.ui.home.HomeRoute

/**
 * Navigation Graph
 */
@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    navAction: NavAction
) {
    navAction.vm = viewModel()

    NavHost(navController = navController,startDestination = NavDestination.HOME_DESTINATION) {
        composable(NavDestination.HOME_DESTINATION) {
            HomeRoute(navAction)
        }
        composable(NavDestination.COFFEE_DESTINATION) {
            CoffeeRoute(navAction)
        }
        composable(NavDestination.WRITE_DIARY_DESTINATION) {
            WriteDiary(navAction)
        }
        composable(NavDestination.READ_DIARY_DESTINATION) {
            ReadDiary(navAction)
        }
    }
}