package com.heygum88.diri.vm

import androidx.lifecycle.ViewModel
import com.heygum88.diri.data.Diary

/**
 * maybe is the only one viewModel in the App
 */
class BasicViewModel: ViewModel() {

    var currentDiary: Diary? = null

}