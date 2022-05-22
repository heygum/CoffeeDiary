package com.heygum88.diri.ui.diary

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.heygum88.diri.ui.NavAction
import androidx.compose.ui.platform.LocalContext
import com.heygum88.diri.data.Diary
import com.heygum88.diri.db.MDb
import com.heygum88.diri.ui.theme.Blue200
import kotlinx.coroutines.launch

@Composable
fun WriteDiary(navAction: NavAction) {

    // this is the text user type
    var text by remember { mutableStateOf("") }

    val context = LocalContext.current
    val autoFocus = FocusRequester() // focus

    // auto focus to the textField when income
    LaunchedEffect(Unit){
        autoFocus.requestFocus()
    }

    Scaffold(
        topBar = {
            TopBar(context,navAction,text)
        }
    ) {
        Box(modifier = Modifier
            .padding(it)
            .padding(horizontal = 20.dp)){
            TextField(value = text,  onValueChange = {text = it},
            modifier = Modifier.fillMaxWidth()
                .focusRequester(autoFocus),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = Blue200
                ))
        }
    }
}

@Composable
fun TopBar(context:Context,navAction: NavAction,text: String) {

    val scope = rememberCoroutineScope()

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
        backgroundColor = Color.Transparent,
        actions = {
            IconButton(onClick = {
                scope.launch {
                    if(createNewDiary(context,text)) {
                        // finish to add diary to database
                        navAction.goBack()
                    } else {
                        Toast.makeText(context,"ERROR",Toast.LENGTH_SHORT).show()
                    }
                }
            }) {
                Icon(Icons.Filled.Done, "doneIcon")
            }
        }
    )
}

/**
 * Create the new diary
 */
suspend fun createNewDiary(context: Context,text: String): Boolean {
    val diary = Diary(body = text)
    MDb.getInstance(context).DiaryDao().insertDiary(diary)

    val diaryId = diary.id

    return MDb.getInstance(context).DiaryDao().getDiary(diaryId).let { true }
}