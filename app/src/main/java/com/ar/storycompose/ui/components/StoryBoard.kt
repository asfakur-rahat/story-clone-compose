package com.ar.storycompose.ui.components

import android.view.ViewConfiguration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoryBoard(
    ip: PaddingValues,
    imageList: List<Int>,
    modifier: Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { imageList.size })
    val coroutineScope = rememberCoroutineScope()

    var currentPage by remember {
        mutableStateOf(0)
    }

    var stopAnimation by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                stopAnimation = true
            }
    ) {
        StoryImage(pagerState = pagerState, imageList = imageList)

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.5f)
                .align(Alignment.CenterStart)
                .pointerInput(Unit){
                    detectTapGestures(onPress = {
                        stopAnimation = true
                        val startTime = System.currentTimeMillis()
                        tryAwaitRelease()
                        val endTime = System.currentTimeMillis()
                        val duration = endTime - startTime
                        if(duration < ViewConfiguration.getLongPressTimeout()){
                            stopAnimation = false
                            coroutineScope.launch {
                                if (currentPage > 0) {
                                    currentPage--
                                }
                                pagerState.animateScrollToPage(currentPage)
                            }
                        }else{
                            stopAnimation = false
                        }
                    })
                }
                .background(Color.Transparent)
        ) {}

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.5f)
                .align(Alignment.CenterEnd)
                .pointerInput(Unit){
                    detectTapGestures(onPress = {
                        stopAnimation = true
                        val startTime = System.currentTimeMillis()
                        tryAwaitRelease()
                        val endTime = System.currentTimeMillis()
                        val duration = endTime - startTime
                        if(duration < ViewConfiguration.getLongPressTimeout()){
                            stopAnimation = false
                            coroutineScope.launch {
                                if (currentPage < imageList.size - 1) {
                                    currentPage++
                                }
                                pagerState.animateScrollToPage(currentPage)
                            }
                        }else{
                            stopAnimation = false
                        }
                    })
                }
                .background(Color.Transparent)
        ) {}

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = ip.calculateTopPadding()),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.padding(2.dp))

            for (index in imageList.indices) {
                HorizontalIndicator(
                    modifier = Modifier.weight(1f),
                    startProgress = index == currentPage,
                    currentPage = currentPage,
                    index = index,
                    stopAnimation = stopAnimation,
                ) {
                    coroutineScope.launch {
                        if (currentPage < imageList.size - 1) {
                            currentPage++
                        }else{
                            currentPage = 0
                        }
                        pagerState.animateScrollToPage(currentPage)
                    }
                }
                Spacer(modifier = Modifier.padding(2.dp))
            }
        }
    }
}