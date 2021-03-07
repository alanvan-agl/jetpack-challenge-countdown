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

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.progressColorOrange

@Composable
fun DurationDisplay(
    modifier: Modifier,
    reachingEnd: Boolean,
    hourValue: Int,
    minuteValue: Int,
    secondValue: Int
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val color = if (reachingEnd) progressColorOrange else MaterialTheme.colors.secondary
        Text(
            color = color,
            text = String.format("%02d", hourValue),
            fontSize = 48.sp
        )
        Text(
            color = color,
            text = ":",
            fontSize = 48.sp
        )
        Text(
            color = color,
            text = String.format("%02d", minuteValue),
            fontSize = 48.sp
        )
        Text(
            color = color,
            text = ":",
            fontSize = 48.sp
        )
        Text(
            color = color,
            text = String.format("%02d", secondValue),
            fontSize = 48.sp
        )
    }
}
