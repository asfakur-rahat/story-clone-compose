package com.ar.storycompose.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun HorizontalIndicator(
    modifier: Modifier,
    startProgress: Boolean = false,
    currentPage: Int = 0,
    index: Int = 0,
    stopAnimation: Boolean = false,
    onAnimationEnd: () -> Unit,
) {

    var progress by remember {
        mutableFloatStateOf(0.0f)
    }

    var isAnimating by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = stopAnimation) {
        isAnimating = !stopAnimation
    }

    LaunchedEffect(currentPage) {
        progress = if (index > currentPage) {
            0.0f
        } else {
            if (startProgress) {
                0.0f
            } else {
                1.0f
            }
        }
    }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        visibilityThreshold = 1f,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )


    if (startProgress){
        LaunchedEffect(isAnimating){
            while (isAnimating && progress < 1f) {
                progress += 0.01f
                delay(30)
            }
            if(progress >= 1f){
                onAnimationEnd()
            }
        }
    }

    LinearProgressIndicator(
        progress = { animatedProgress },
        color = Color.White,
        trackColor = Color.LightGray,
        modifier = modifier
            .padding(top = 12.dp, bottom = 12.dp)
            .clip(RoundedCornerShape(12.dp))
    )
}