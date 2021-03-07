package com.example.androiddevchallenge.ui

import androidx.compose.runtime.*
import com.example.androiddevchallenge.MainViewModel

@Composable
fun CountDownScreen(viewModel: MainViewModel, controlState: ControlState) {
    when (controlState) {
        ControlState.IDLE -> {
            DurationSelector(viewModel)
        }
        ControlState.RUNNING, ControlState.PAUSED -> {
            CountDown(viewModel)
        }
    }
}