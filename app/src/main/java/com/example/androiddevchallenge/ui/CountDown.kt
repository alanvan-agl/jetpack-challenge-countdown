package com.example.androiddevchallenge.ui

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.MainViewModel
import com.example.androiddevchallenge.ui.theme.primaryLightVariant
import com.example.androiddevchallenge.ui.theme.progressColor

@Composable
fun CountDown(viewModel: MainViewModel) {
    val elapsedTime by viewModel.elapsedTimeInMillis.collectAsState()
    val totalRemainingTimeInSeconds by viewModel.requiredTimeInMillis.collectAsState()
    val progress: Float = elapsedTime.toFloat() / totalRemainingTimeInSeconds
    Box {
        Canvas(
            modifier = Modifier
                .requiredWidth(320.dp)
                .requiredHeight(320.dp)
        ) {
            val (canvasWidth, canvasHeight) = size.width to size.height
            drawCircle(
                color = primaryLightVariant,
                radius = size.minDimension / 2,
                center = Offset(canvasWidth / 2, canvasHeight / 2),
                style = Stroke(width = 5.dp.toPx(), cap = StrokeCap.Square)
            )
        }
        Canvas(
            modifier = Modifier
                .requiredWidth(320.dp)
                .requiredHeight(320.dp)
        ) {
            drawArc(
                color = progressColor,
                startAngle = -90f,
                sweepAngle = 360f - progress * 360f,
                useCenter = false,
                style = Stroke(width = 5.dp.toPx(), cap = StrokeCap.Square)
            )
        }
    }
}
