package com.heygum88.coffeediary.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.heygum88.coffeediary.data.Diary
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.Flow

/**
 * maybe is the only one viewModel in the App
 */
class BasicViewModel: ViewModel() {

    var currentDiary: Diary? = null

}