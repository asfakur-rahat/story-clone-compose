package com.ar.storycompose.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import com.ar.storycompose.R
import com.ar.storycompose.ui.components.StoryBoard


val imageList = listOf<Int>(
    R.drawable.image1,
    R.drawable.image2,
    R.drawable.image3,
    R.drawable.image4,
    R.drawable.image5

)

@Composable
fun HomeScreen(){
    Scaffold { ip ->
        StoryBoard(ip, imageList = imageList, modifier = Modifier.padding(start = ip.calculateStartPadding(LayoutDirection.Ltr), end = ip.calculateEndPadding(LayoutDirection.Ltr), bottom = ip.calculateBottomPadding()))
    }
}

@Composable
fun HomeScreenSkeleton() {

}

@Composable
fun HomeScreenContent() {

}

@Preview
@Composable
private fun HomeScreenPreview() {
    Surface {
        HomeScreen()
    }
}