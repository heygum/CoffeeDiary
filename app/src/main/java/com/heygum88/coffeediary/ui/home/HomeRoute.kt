package com.heygum88.coffeediary.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.heygum88.coffeediary.ui.NavAction


const val TAG = "home"

@Composable
fun HomeRoute(navAction: NavAction) {
    Scaffold {
        Column() {
            Text(text = "This is home")
        }
    }
}
