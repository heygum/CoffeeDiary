package com.heygum88.diri.ui.coffee

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import com.heygum88.diri.ui.NavAction

/**
 * Coffee page
 */
const val TAG = "coffee"
@Composable
fun CoffeeRoute(navAction: NavAction) {
    Scaffold {
        Column() {
            Icon(Icons.Default.Warning, contentDescription = "",Modifier.fillMaxSize().scale(0.1f))
        }
    }
}