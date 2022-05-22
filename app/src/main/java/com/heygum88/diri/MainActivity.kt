package com.heygum88.diri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.heygum88.diri.ui.CoffeeDiaryApp
import com.heygum88.diri.ui.theme.CoffeeDiaryTheme


/**
 * The main activity of the app
 * Keep it clear
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeDiaryTheme() {
                CoffeeDiaryApp()
            }
        }
    }
}
