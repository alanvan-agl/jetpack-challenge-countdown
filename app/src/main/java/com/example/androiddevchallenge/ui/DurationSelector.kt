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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.MainViewModel
import com.example.androiddevchallenge.R

@Composable
fun DurationSelector(requiredSize: Dp, viewModel: MainViewModel) {
    val currentHour = viewModel.hour.collectAsState()
    val currentMinute = viewModel.minute.collectAsState()
    val currentSecond = viewModel.second.collectAsState()
    Row(
        modifier = Modifier
            .requiredWidth(requiredSize)
            .requiredHeight(requiredSize),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NumberSelector(
            type = stringResource(id = R.string.hours),
            currentValue = currentHour.value,
            onDurationIncreased = {
                viewModel.increaseDuration(it, MainViewModel.DurationType.HOUR)
            },
            onDurationDecreased = {
                viewModel.decreaseDuration(it, MainViewModel.DurationType.HOUR)
            }
        )
        Text(
            modifier = Modifier.padding(PaddingValues(top = 100.dp)),
            color = MaterialTheme.colors.secondary,
            text = ":",
            fontSize = 48.sp
        )
        NumberSelector(
            type = stringResource(id = R.string.minutes),
            currentValue = currentMinute.value,
            onDurationIncreased = {
                viewModel.increaseDuration(it, MainViewModel.DurationType.MINUTE)
            },
            onDurationDecreased = {
                viewModel.decreaseDuration(it, MainViewModel.DurationType.MINUTE)
            }
        )
        Text(
            modifier = Modifier.padding(PaddingValues(top = 100.dp)),
            color = MaterialTheme.colors.secondary,
            text = ":",
            fontSize = 48.sp
        )
        NumberSelector(
            type = stringResource(id = R.string.seconds),
            currentValue = currentSecond.value,
            onDurationIncreased = {
                viewModel.increaseDuration(it, MainViewModel.DurationType.SECOND)
            },
            onDurationDecreased = {
                viewModel.decreaseDuration(it, MainViewModel.DurationType.SECOND)
            }
        )
    }
}
