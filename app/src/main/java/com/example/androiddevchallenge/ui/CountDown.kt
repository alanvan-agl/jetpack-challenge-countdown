/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.MainViewModel
import com.example.androiddevchallenge.ui.theme.primaryLightVariant
import com.example.androiddevchallenge.ui.theme.progressColor
import com.example.androiddevchallenge.ui.theme.progressColorOrange

@Composable
fun CountDown(requiredSize: Dp, viewModel: MainViewModel) {
    val elapsedTime by viewModel.elapsedTimeInMillis.collectAsState()
    val totalRemainingTimeInSeconds by viewModel.requiredTimeInMillis.collectAsState()
    val progress: Float = elapsedTime.toFloat() / totalRemainingTimeInSeconds
    Box {
        Canvas(
            modifier = Modifier
                .requiredWidth(requiredSize)
                .requiredHeight(requiredSize)
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
                .requiredWidth(requiredSize)
                .requiredHeight(requiredSize)
        ) {
            drawArc(
                color = if (progress <= 0.75) progressColor else progressColorOrange,
                startAngle = -90f,
                sweepAngle = 360f - progress * 360f,
                useCenter = false,
                style = Stroke(width = 5.dp.toPx(), cap = StrokeCap.Square)
            )
        }
        DurationDisplay(
            modifier = Modifier
                .requiredWidth(requiredSize)
                .requiredHeight(requiredSize),
            reachingEnd = progress > 0.75,
            hourValue = viewModel.getHourRemaining(),
            minuteValue = viewModel.getMinuteRemaining(),
            secondValue = viewModel.getSecondsRemaining()
        )
    }
}
