package com.example.androiddevchallenge.ui

import androidx.compose.runtime.*
import com.example.androiddevchallenge.MainViewModel

@Composable
fun CountDownScreen(viewModel: MainViewModel, timerState: TimerState) {
    when (timerState) {
        is TimerState.Idle -> {
            DurationSelector(viewModel)
        }
        TimerState.Running, TimerState.Paused -> {
            CountDown(viewModel)
        }
    }
}