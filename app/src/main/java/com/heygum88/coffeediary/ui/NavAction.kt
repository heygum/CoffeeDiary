package com.heygum88.coffeediary.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController


object NavDestination {
    const val HOME_DESTINATION = "home"
    const val COFFEE_DESTINATION = "coffee"
}

class NavAction(navController: NavHostController) {

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

    fun navigate(destination: String): () -> Unit {
        when(destination) {
            NavDestination.HOME_DESTINATION -> {
                return navigateToHome
            }
            NavDestination.COFFEE_DESTINATION -> {
                return navigateToCoffee
            }
            else -> {
                return navigateToHome
            }
        }
    }

}