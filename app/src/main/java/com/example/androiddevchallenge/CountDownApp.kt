package com.example.androiddevchallenge

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.ui.ControlState
import com.example.androiddevchallenge.ui.Controls
import com.example.androiddevchallenge.ui.CountDownScreen
import com.example.androiddevchallenge.ui.DurationSelector

@Composable
fun CountDownApp(viewModel: MainViewModel) {
    var controlState by remember { mutableStateOf(ControlState.IDLE) }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(top = 32.dp, bottom = 32.dp))
    ) {
        val (countDownScreen, controls) = createRefs()
        Box(modifier = Modifier.constrainAs(countDownScreen) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(controls.top)
        }) {
            CountDownScreen(
                viewModel = viewModel,
                controlState = controlState
            )
        }
        Box(modifier = Modifier.constrainAs(controls) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Controls(
                controlState = controlState,
                onStart = {
                    controlState = ControlState.RUNNING
                },
                onPause = {

                },
                onResume = {

                },
                onCancel = {
                    controlState = ControlState.IDLE
                }
            )
        }
    }
}