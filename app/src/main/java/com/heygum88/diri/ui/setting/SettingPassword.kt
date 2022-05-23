package com.heygum88.diri.ui.setting

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.heygum88.diri.data.User
import com.heygum88.diri.db.MDb
import com.heygum88.diri.tool.OutputTools
import com.heygum88.diri.ui.NavAction
import com.heygum88.diri.ui.theme.Blue200
import com.heygum88.diri.ui.theme.Gray200
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Composable
fun SettingPassword(navAction: NavAction) {
    val TAG = "SettingPassword"

    val context = LocalContext.current
    val db = MDb.getInstance(context)
    val scope = rememberCoroutineScope()
    val openDialog = remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .clickable {
                openDialog.value = true
            },
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ){
        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(text = "Set Password")
                },
                text = {
                    Column() {
                        TextField(
                            value = text,
                            onValueChange = { text = it },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                cursorColor = Blue200
                            )
                        )
                        Spacer(modifier = Modifier.size(20.dp))
                        Text("MUST remeber your password, blank means no password.")
                    }
                },
                buttons = {
                    Row(
                        modifier = Modifier.padding(all = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Gray200),
                            onClick = { openDialog.value = false }
                        ) {
                            Text("Cancel")
                        }
                        Button(
                            modifier = Modifier
                                .padding(horizontal = 20.dp),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Gray200),
                            onClick = {
                                scope.launch {
                                    async {
                                        MDb.getInstance(context).UserDao().insertPw(User(pw = text))
                                    }.await()
                                    Toast.makeText(context,"DONE",Toast.LENGTH_SHORT).show()
                                }
                                openDialog.value = false
                            }
                        ) {
                            Text("Done")
                        }
                    }
                }
            )
        }
        Row {
            Icon(
                Icons.Filled.Lock, contentDescription = "Password",modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically))
            Column(modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterVertically)) {
                Text(text = "Setting Password")
            }
        }
    }
}