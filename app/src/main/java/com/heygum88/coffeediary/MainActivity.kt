package com.heygum88.coffeediary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.heygum88.coffeediary.ui.CoffeeDiaryApp
import com.heygum88.coffeediary.ui.theme.CoffeeDiaryTheme


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
