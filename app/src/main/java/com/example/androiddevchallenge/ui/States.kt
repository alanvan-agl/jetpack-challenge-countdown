package com.example.androiddevchallenge.ui

sealed class TimerState {
    object Running: TimerState()
    object Idle: TimerState()
    object Paused: TimerState()
}

enum class AlertState {
    NO_ALERT, ZERO_DURATION, ALARM
}