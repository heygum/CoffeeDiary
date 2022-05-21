package com.heygum88.coffeediary.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.heygum88.coffeediary.data.Diary
import com.heygum88.coffeediary.db.MDb
import com.heygum88.coffeediary.ui.NavAction
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


const val TAG = "home"

/**
 * Name as Home, actually it's a diary page
 */
@Composable
fun HomeRoute(navAction: NavAction) {
    val scope = rememberCoroutineScope()
    Scaffold {

        val context = LocalContext.current

        // diary list
        var mList by remember {
            mutableStateOf(
                listOf(Diary(body = ""))
            )
        }

        // Using FLow to update list
        scope.launch {
            MDb.getInstance(context).DiaryDao().getAll().collect {
                mList = it
            }
        }

        // Diary List
        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp,vertical = 8.dp),
            modifier = Modifier.fillMaxWidth()){
                items(
                    items = mList,
                    itemContent = {
                        DiaryListItem(diary = it)
                    }
                )
        }

        // Click button to write a diary
        Button(onClick = {
                val diary = Diary(body = "hello")
                MDb.getInstance(context).DiaryDao().insertDiary(diary)
            }) {
                Text(text = "CLICK")
        }
    }
}


