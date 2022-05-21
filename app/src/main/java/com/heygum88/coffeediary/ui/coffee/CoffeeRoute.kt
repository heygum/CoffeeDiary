package com.heygum88.coffeediary.ui.coffee

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.heygum88.coffeediary.ui.NavAction

/**
 * Coffee page
 */
const val TAG = "coffee"
@Composable
fun CoffeeRoute(navAction: NavAction) {
    Scaffold {
        Column() {
            Text(text = "This is coffee")
        }
    }
}