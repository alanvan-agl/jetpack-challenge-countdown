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

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.ui.AlertState
import com.example.androiddevchallenge.ui.TimerState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : ViewModel() {
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
    val timerState = MutableStateFlow<TimerState>(TimerState.Idle)
    val alertState = MutableSharedFlow<AlertState>(extraBufferCapacity = Int.MAX_VALUE)

    val hour = MutableStateFlow(0)
    val minute = MutableStateFlow(0)
    val second = MutableStateFlow(0)
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
        timerState.value = TimerState.Paused
        timer?.cancel()
        timer = null
    }

    fun cancelTimer() {
        timerState.value = TimerState.Idle
        timer?.cancel()
        timer = null
        elapsedTimeInMillis.value = 0
    }

    fun startTimer() {
        requiredTimeInMillis.value = (hour.value * 3_600 + minute.value * 60 + second.value) * 1_000L
        val millisInFuture = getMillisRemaining()
        if (millisInFuture > 0) {
            timerState.value = TimerState.Running
            timer = object : CountDownTimer(millisInFuture, 1) {
                override fun onTick(millisUntilFinished: Long) {
                    elapsedTimeInMillis.value = requiredTimeInMillis.value - millisUntilFinished
                }
                override fun onFinish() {
                    timer = null
                    elapsedTimeInMillis.value = 0
                    timerState.value = TimerState.Idle
                }
            }
            timer?.start()
        } else {
            alertState.tryEmit(AlertState.ZERO_DURATION)
        }
    }

    fun getMillisRemaining() = requiredTimeInMillis.value - elapsedTimeInMillis.value

    fun getHourRemaining() = ((getMillisRemaining() / (1_000 * 60 * 60)) % 60).toInt()
    fun getMinuteRemaining() = ((getMillisRemaining() / (1_000 * 60)) % 60).toInt()
    fun getSecondsRemaining() = ((getMillisRemaining() / 1_000) % 60).toInt()

    enum class DurationType {
        HOUR, MINUTE, SECOND
    }
}
