package com.heygum88.diri.ui.diary

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.heygum88.diri.data.Diary
import com.heygum88.diri.tool.DateFormatter
import com.heygum88.diri.ui.NavAction

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
        elevation = 0.dp,
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