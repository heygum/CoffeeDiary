package com.heygum88.coffeediary.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.heygum88.coffeediary.vm.BasicViewModel


/**
 * Using this to name each destination
 */
object NavDestination {
    const val HOME_DESTINATION = "home"
    const val COFFEE_DESTINATION = "coffee"
    const val WRITE_DIARY_DESTINATION = "write_diary"
    const val READ_DIARY_DESTINATION = "read_diary"
}

class NavAction(navController: NavHostController) {

    val mNavController = navController

    var vm: BasicViewModel? = null

    val navigateToHome: () -> Unit = {
        navController.navigate(NavDestination.HOME_DESTINATION) {

            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }

            // Avoid multiple copies of the same destination when
            // reselecting the same item
//            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState =true
        }
    }

    val navigateToCoffee:() -> Unit = {
        navController.navigate(NavDestination.COFFEE_DESTINATION) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
//            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToWriteDiary:() -> Unit = {
        navController.navigate(NavDestination.WRITE_DIARY_DESTINATION) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            restoreState = true
        }
    }

    val navigateToReadDiary:() -> Unit = {
        navController.navigate(NavDestination.READ_DIARY_DESTINATION) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            restoreState = true
        }
    }

    /**
     * Navigate
     */
    fun navigate(destination: String) {
        when(destination) {
            NavDestination.HOME_DESTINATION -> {
                navigateToHome.invoke()
            }
            NavDestination.COFFEE_DESTINATION -> {
                navigateToCoffee.invoke()
            }
            NavDestination.WRITE_DIARY_DESTINATION -> {
                navigateToWriteDiary.invoke()
            }
            NavDestination.READ_DIARY_DESTINATION -> {
                navigateToReadDiary.invoke()
            }
            else -> {
                navigateToHome.invoke()
            }
        }
    }

    fun goBack() {
        mNavController.popBackStack()
    }

}