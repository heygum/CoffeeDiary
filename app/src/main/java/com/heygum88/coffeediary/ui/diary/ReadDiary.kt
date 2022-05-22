package com.heygum88.coffeediary.ui.diary

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.heygum88.coffeediary.data.Diary
import com.heygum88.coffeediary.tool.DateFormatter
import com.heygum88.coffeediary.ui.NavAction
import com.heygum88.coffeediary.vm.BasicViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun ReadDiary(navAction: NavAction) {
    val currentDiary: Diary? = navAction.vm?.currentDiary
    Scaffold(
        topBar = {
            ReadTopBar(navAction)
        }
    ) {
        Box(modifier = Modifier
            .padding(it)
            .padding(horizontal = 20.dp)){

            currentDiary?.let {
                Column() {
                    Text(text = DateFormatter.millsToDate(currentDiary.date))
                    Spacer(modifier = Modifier.size(30.dp))
                    Text(text = currentDiary.body)
                }
            }
        }
    }
}

@Composable
fun ReadTopBar(navAction: NavAction) {
    TopAppBar(
        modifier = Modifier.padding(4.dp),
        title = {
            Text(text = "")
        },
        navigationIcon = {
            IconButton(onClick = { navAction.goBack() }) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        },
        backgroundColor = Color.Transparent
    )
}