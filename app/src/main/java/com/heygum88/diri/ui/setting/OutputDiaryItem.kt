package com.heygum88.diri.ui.setting

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.heygum88.diri.db.MDb
import com.heygum88.diri.tool.OutputTools
import com.heygum88.diri.ui.NavAction
import kotlinx.coroutines.*

@Composable
fun OutputDiaryItem(navAction: NavAction) {

    val TAG = "OutputDiaryItem"

    val context = LocalContext.current
    val db = MDb.getInstance(context)
    val scope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .clickable {
                scope.launch(Dispatchers.IO) {
                    async {
                        OutputTools.outputAllDiaries(db)
                    }.await()
                    launch(Dispatchers.Main) {
                        Toast.makeText(context,"Finish",Toast.LENGTH_SHORT).show()
                    }
                }
            },
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ){
        Row {
            Icon(Icons.Filled.Share, contentDescription = "Setting",modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterVertically))
            Column(modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterVertically)) {
                Text(text = "Output All Diaries")
            }
        }
    }
}