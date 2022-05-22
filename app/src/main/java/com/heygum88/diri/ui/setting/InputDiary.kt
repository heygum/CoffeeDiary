package com.heygum88.diri.ui.setting

import android.os.Environment
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.heygum88.diri.db.MDb
import com.heygum88.diri.tool.InputTools
import com.heygum88.diri.tool.OutputTools
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Composable
fun InputDiary() {
    val TAG = "OutputDiaryItem"

    val context = LocalContext.current
    val db = MDb.getInstance(context)
    val scope = rememberCoroutineScope()
    val filePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()){
        val items = context.contentResolver.openInputStream(it)
        if(!InputTools.inputSpecificDiary(items,db)) {
            Toast.makeText(context,"Failed to load file",Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
        }
    }

    Card(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .clickable {
                filePicker.launch("*/*")
            },
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ){
        Row {
            Icon(
                Icons.Filled.AccountCircle, contentDescription = "Input",modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically))
            Column(modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterVertically)) {
                Text(text = "Input Diaries From File")
            }
        }
    }
}