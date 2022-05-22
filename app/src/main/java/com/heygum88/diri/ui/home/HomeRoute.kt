package com.heygum88.diri.ui.home

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.heygum88.diri.data.Diary
import com.heygum88.diri.db.MDb
import com.heygum88.diri.ui.NavAction
import com.heygum88.diri.ui.theme.Gray200
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


const val TAG = "home"

/**
 * Name as Home, actually it's a diary page
 */
@Composable
fun HomeRoute(navAction: NavAction) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Scaffold(floatingActionButton = {
        AddDiaryFloatButton(context,navAction)
    }) {
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
                        DiaryListItem(diary = it,navAction)
                    }
                )
        }
    }
}

@Composable
fun AddDiaryFloatButton(context: Context,navAction: NavAction) {
    FloatingActionButton(onClick = {
          navAction.navigateToWriteDiary.invoke()
    },
    backgroundColor = Gray200) {
        Icon(Icons.Filled.Add,"")
    }
    
}

