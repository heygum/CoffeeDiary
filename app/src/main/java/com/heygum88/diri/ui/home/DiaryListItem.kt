package com.heygum88.diri.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.heygum88.diri.data.Diary
import com.heygum88.diri.tool.DateFormatter
import com.heygum88.diri.ui.NavAction
import com.heygum88.diri.ui.NavDestination
import com.heygum88.diri.ui.theme.Blue200

/**
 * Each card of Diary in the home page
 */
@Composable
fun DiaryListItem(diary: Diary,navAction: NavAction) {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                navAction.vm?.currentDiary = diary
                navAction.navigate(NavDestination.READ_DIARY_DESTINATION)
            },
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ){
        Row {
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.CenterVertically)) {
                Box(
                    Modifier
                        .width(LocalConfiguration.current.screenWidthDp.dp / 10)
                        .padding(10.dp)
                        .size(4.dp)
                        .background(Blue200))
                Text(text = DateFormatter.millsToDate(diary.date))
            }
        }
    }

}