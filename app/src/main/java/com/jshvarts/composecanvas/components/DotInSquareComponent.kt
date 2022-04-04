package com.jshvarts.composecanvas.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jshvarts.composecanvas.ui.theme.ComposeCanvasGettingStartedTheme
import com.jshvarts.composecanvas.ui.theme.Purple200
import com.jshvarts.composecanvas.ui.theme.Teal200

@Composable
fun DotInSquareComponent(componentSize: Dp = 300.dp) {
    val canvasSizePx = with(LocalDensity.current) {
        componentSize.toPx()
    }

    val infiniteScale = rememberInfiniteTransition()

    val dotScaleAnimation by infiniteScale.animateFloat(
        initialValue = 20f,
        targetValue = canvasSizePx / 2,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(
        modifier = Modifier
            .size(componentSize)
    ) {
        myBox()
        myPulsatingDot(dotScaleAnimation)
    }
}

fun DrawScope.myBox() {
    drawRect(
        Brush.linearGradient(
            colors = listOf(Purple200, Teal200)
        ),
        size = size
    )
}

fun DrawScope.myPulsatingDot(
    dotScaleAnimation: Float
) {
    drawCircle(
        color = Color.White,
        center = Offset(x = size.width / 2f, y = size.height / 2f),
        radius = dotScaleAnimation
    )
}

@Preview(showBackground = true)
@Composable
fun DotInSquareComponentPreview() {
    ComposeCanvasGettingStartedTheme {
        DotInSquareComponent(100.dp)
    }
}