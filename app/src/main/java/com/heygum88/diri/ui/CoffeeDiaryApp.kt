package com.heygum88.diri.ui


import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.heygum88.diri.db.MDb
import com.heygum88.diri.ui.theme.Blue200
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


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
    navigationActions.vm = viewModel()
    var lock by remember {
        mutableStateOf(true)
    }
    var pw by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    var realPw = ""


    if(lock) {
        // lock screen
        Box(modifier = Modifier
            .padding(horizontal = 20.dp)){

            scope.launch {
                    val list = MDb.getInstance(context).UserDao().getAll()
                    if(list.isNotEmpty()) {
                        realPw = MDb.getInstance(context).UserDao().getPw()
                    } else {
                        realPw = ""
                    }
            }
            TextField(value = pw,  onValueChange = {
                pw = it
                if(pw.equals(realPw)) {
                    lock = false
                }},
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = Blue200
                ))
        }

    } else {
        BottomBar(navController = navController, navAction = navigationActions)
    }

}

/**
 * Bottom Bar of the app
 */
@Composable
fun BottomBar(navController: NavHostController,navAction: NavAction) {

    val items = listOf(
        NavDestination.HOME_DESTINATION,
        NavDestination.COFFEE_DESTINATION,
        NavDestination.SETTING_DESTINATION
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
                            navAction.navigate(item)
                        })
                }
            }
        }) {
        Box(modifier = Modifier.padding(it)){
            NavGraph(navController,navAction)
        }
    }
}
