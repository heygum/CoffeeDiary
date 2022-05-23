package com.heygum88.diri.ui.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.heygum88.diri.ui.NavAction

@Composable
fun SettingRoute(navAction: NavAction) {
    Scaffold() {
        Column {
            ASpacer()
            OutputDiaryItem(navAction)
            ASpacer()
            InputDiary()
            ASpacer()
            SettingPassword(navAction)
        }
    }
}

@Composable
fun ASpacer(){
    Spacer(modifier = Modifier.padding(vertical = 12.dp))
}