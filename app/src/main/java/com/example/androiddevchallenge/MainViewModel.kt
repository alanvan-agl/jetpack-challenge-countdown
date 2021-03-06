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
    val durationInSeconds = MutableStateFlow(0)

    val hour = MutableStateFlow(0)
    val minute = MutableStateFlow(0)
    val second = MutableStateFlow(0)

    val timerState = MutableStateFlow<TimerState>(TimerState.Idle)

    var timer: CountDownTimer? = null

    fun setDurationInSeconds(time: Int) {
        durationInSeconds.value = time
    }

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

    fun resetDuration() {
        hour.value = 0
        minute.value = 0
        second.value = 0
    }

    fun cancelTimer() {
        timer?.cancel()
        timer = null
        timerState.value = TimerState.Idle
    }

    fun startTimer() {
        timer = object: CountDownTimer(durationInSeconds.value * 1_000L, 1_000L) {
            override fun onTick(millisUntilFinished: Long) {
                durationInSeconds.value = (millisUntilFinished / 1_000L).toInt()
            }
            override fun onFinish() {
                timer = null
                timerState.value = TimerState.Idle
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
