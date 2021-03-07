package com.example.androiddevchallenge.ui

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.*
import com.example.androiddevchallenge.MainViewModel

@Composable
fun CountDownScreen(viewModel: MainViewModel, controlState: ControlState) {
    Crossfade(targetState = controlState) {
        when (controlState) {
            ControlState.IDLE -> {
                DurationSelector(viewModel)
            }
            ControlState.RUNNING -> {

            }
        }
    }
}