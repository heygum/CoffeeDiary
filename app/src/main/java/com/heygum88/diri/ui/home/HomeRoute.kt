package com.heygum88.diri.ui.home

import android.content.Context
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.heygum88.diri.data.Diary
import com.heygum88.diri.db.MDb
import com.heygum88.diri.ui.NavAction
import com.heygum88.diri.ui.theme.Blue200
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

        val state = rememberLazyListState()

        // Diary List
        LazyColumn(state = state, contentPadding = PaddingValues(horizontal = 16.dp,vertical = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .simpleVerticalScrollbar(state)){
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

@Composable
fun Modifier.simpleVerticalScrollbar(
    state: LazyListState,
    width: Dp = 8.dp
): Modifier {
    val targetAlpha = if (state.isScrollInProgress) 1f else 0f
    val duration = if (state.isScrollInProgress) 150 else 500

    val alpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(durationMillis = duration)
    )


    return drawWithContent {
        drawContent()
        val firstVisibleElementIndex = state.layoutInfo.visibleItemsInfo.firstOrNull()?.index
        val needDrawScrollbar = state.isScrollInProgress || alpha > 0.0f
        if (needDrawScrollbar && firstVisibleElementIndex != null) {
            val elementHeight = this.size.height / state.layoutInfo.totalItemsCount
            val scrollbarOffsetY = firstVisibleElementIndex * elementHeight
            val scrollbarHeight = state.layoutInfo.visibleItemsInfo.size * elementHeight
            drawRect(
                color = Blue200,
                topLeft = Offset(this.size.width - width.toPx(), scrollbarOffsetY),
                size = Size(width.toPx(), scrollbarHeight),
                alpha = alpha
            )
        }
    }
}
