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
package com.example.androiddevchallenge

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.ui.Controls
import com.example.androiddevchallenge.ui.CountDownScreen

@Composable
fun CountDownApp(viewModel: MainViewModel) {
    val timerState by viewModel.timerState.collectAsState()
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(top = 32.dp, bottom = 32.dp))
    ) {
        val (countDownScreen, controls) = createRefs()
        Box(
            modifier = Modifier.constrainAs(countDownScreen) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(controls.top)
            }
        ) {
            CountDownScreen(
                viewModel = viewModel,
                timerState = timerState
            )
        }
        Box(
            modifier = Modifier.constrainAs(controls) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Controls(
                timerState = timerState,
                onStart = { viewModel.startTimer() },
                onPause = { viewModel.pauseTimer() },
                onCancel = { viewModel.cancelTimer() }
            )
        }
    }
}
