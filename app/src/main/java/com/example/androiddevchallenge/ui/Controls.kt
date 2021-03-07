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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.cancelButtonColor
import com.example.androiddevchallenge.ui.theme.pauseButtonColor
import com.example.androiddevchallenge.ui.theme.startAndResumeButtonColor

@Composable
fun Controls(
    timerState: TimerState,
    onStart: () -> Unit,
    onPause: () -> Unit,
    onCancel: () -> Unit
) {
    val buttonWidth = 100.dp
    val buttonHeight = 50.dp
    val buttonCornerRadius = 25.dp
    when (timerState) {
        is TimerState.Idle -> {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(
                    modifier = Modifier.width(buttonWidth).height(buttonHeight),
                    onClick = onStart,
                    colors = ButtonDefaults.buttonColors(backgroundColor = startAndResumeButtonColor),
                    shape = RoundedCornerShape(buttonCornerRadius)
                ) {
                    Text(
                        text = stringResource(id = R.string.start),
                        color = MaterialTheme.colors.secondary,
                        fontSize = 18.sp
                    )
                }
            }
        }
        is TimerState.Running -> {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextButton(
                    modifier = Modifier.width(buttonWidth).height(buttonHeight),
                    onClick = onPause,
                    colors = ButtonDefaults.buttonColors(backgroundColor = pauseButtonColor),
                    shape = RoundedCornerShape(buttonCornerRadius)
                ) {
                    Text(
                        text = stringResource(id = R.string.pause),
                        color = MaterialTheme.colors.secondary,
                        fontSize = 18.sp
                    )
                }
                TextButton(
                    modifier = Modifier.width(buttonWidth).height(buttonHeight),
                    onClick = onCancel,
                    colors = ButtonDefaults.buttonColors(backgroundColor = cancelButtonColor),
                    shape = RoundedCornerShape(buttonCornerRadius)
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        color = MaterialTheme.colors.secondary,
                        fontSize = 18.sp
                    )
                }
            }
        }
        is TimerState.Paused -> {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextButton(
                    modifier = Modifier.width(buttonWidth).height(buttonHeight),
                    onClick = onStart,
                    colors = ButtonDefaults.buttonColors(backgroundColor = startAndResumeButtonColor),
                    shape = RoundedCornerShape(buttonCornerRadius)
                ) {
                    Text(
                        text = stringResource(id = R.string.resume),
                        color = MaterialTheme.colors.secondary,
                        fontSize = 18.sp
                    )
                }
                TextButton(
                    modifier = Modifier.width(buttonWidth).height(buttonHeight),
                    onClick = onCancel,
                    colors = ButtonDefaults.buttonColors(backgroundColor = cancelButtonColor),
                    shape = RoundedCornerShape(buttonCornerRadius)
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        color = MaterialTheme.colors.secondary,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}
