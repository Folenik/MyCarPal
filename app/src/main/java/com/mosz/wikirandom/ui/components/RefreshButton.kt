package com.mosz.wikirandom.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mosz.wikirandom.R

@Composable
fun RefreshButton(onRefreshButtonClick: () -> Unit) {
    val isRotated = remember {
        mutableStateOf(false)
    }
    val angle: Float by animateFloatAsState(
        targetValue = if (isRotated.value) 180f else 0f,
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearEasing
        ),
        finishedListener = { isRotated.value = false }, label = ""
    )
    IconButton(onClick = {
        onRefreshButtonClick()
        isRotated.value = !isRotated.value
    }, modifier = Modifier.size(64.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_refresh),
            contentDescription = stringResource(R.string.refresh),
            modifier = Modifier
                .rotate(angle)
                .fillMaxSize()
        )
    }
}
