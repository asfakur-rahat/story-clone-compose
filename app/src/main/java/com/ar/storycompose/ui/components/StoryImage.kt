package com.ar.storycompose.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoryImage(pagerState: PagerState, imageList: List<Int>) {
    HorizontalPager(state = pagerState, userScrollEnabled = false) {
        AsyncImage(
            model = imageList[it],
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
}