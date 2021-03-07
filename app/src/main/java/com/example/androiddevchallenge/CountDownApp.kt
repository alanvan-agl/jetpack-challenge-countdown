package com.example.androiddevchallenge

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.ui.TimerState
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
        Box(modifier = Modifier.constrainAs(countDownScreen) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(controls.top)
        }) {
            CountDownScreen(
                viewModel = viewModel,
                timerState = timerState
            )
        }
        Box(modifier = Modifier.constrainAs(controls) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Controls(
                timerState = timerState,
                onStart = { viewModel.startTimer() },
                onPause = { viewModel.pauseTimer() },
                onCancel = { viewModel.cancelTimer() }
            )
        }
    }
}