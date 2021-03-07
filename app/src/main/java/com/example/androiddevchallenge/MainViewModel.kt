package com.example.androiddevchallenge

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel: ViewModel() {
    companion object {
        private const val MAX_HOUR = 24
        private const val MIN_HOUR = 0
        private const val MAX_MINUTE = 60
        private const val MIN_MINUTE = 0
        private const val MAX_SECOND = 60
        private const val MIN_SECOND = 0
    }
    val elapsedTimeInMillis = MutableStateFlow(0L)
    val requiredTimeInMillis = MutableStateFlow(0L)

    val hour = MutableStateFlow(0)
    val minute = MutableStateFlow(0)
    val second = MutableStateFlow(0)

    val timerState = MutableStateFlow<TimerState>(TimerState.Idle)

    var timer: CountDownTimer? = null

    fun increaseDuration(duration: Int, type: DurationType) {
        when (type) {
            DurationType.HOUR -> hour.value = if (duration == MAX_HOUR) {
                MIN_HOUR
            } else {
                duration + 1
            }
            DurationType.MINUTE -> minute.value = if (duration == MAX_MINUTE) {
                MIN_MINUTE
            } else {
                duration + 1
            }
            DurationType.SECOND -> second.value = if (duration == MAX_SECOND) {
                MIN_SECOND
            } else {
                duration + 1
            }
        }
    }

    fun decreaseDuration(duration: Int, type: DurationType) {
        when (type) {
            DurationType.HOUR -> hour.value = if (duration == MIN_HOUR) {
                MAX_HOUR
            } else {
                duration - 1
            }
            DurationType.MINUTE -> minute.value = if (duration == MIN_MINUTE) {
                MAX_MINUTE
            } else {
                duration - 1
            }
            DurationType.SECOND -> second.value = if (duration == MIN_SECOND) {
                MAX_SECOND
            } else {
                duration - 1
            }
        }
    }

    fun pauseTimer() {
        timer?.cancel()
        timer = null
        timerState.value = TimerState.Idle
    }

    fun cancelTimer() {
        pauseTimer()
        elapsedTimeInMillis.value = 0
    }

    fun startTimer(fromScratch: Boolean = true) {
        requiredTimeInMillis.value = (hour.value * 3_600 + minute.value * 60 + second.value) * 1_000L
        timer = object: CountDownTimer(requiredTimeInMillis.value - elapsedTimeInMillis.value, 1) {
            override fun onTick(millisUntilFinished: Long) {
                elapsedTimeInMillis.value = requiredTimeInMillis.value - millisUntilFinished
            }
            override fun onFinish() {
                timer = null
                timerState.value = TimerState.Idle
                elapsedTimeInMillis.value = 0
            }
        }
        timer?.start()
        timerState.value = TimerState.Counting
    }

    enum class DurationType {
        HOUR, MINUTE, SECOND
    }

    sealed class TimerState {
        object Idle: TimerState()
        object Counting: TimerState()
    }
}
