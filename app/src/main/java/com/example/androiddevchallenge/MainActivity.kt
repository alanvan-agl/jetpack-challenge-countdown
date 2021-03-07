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

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.androiddevchallenge.service.AlertReceiver
import com.example.androiddevchallenge.ui.AlertState
import com.example.androiddevchallenge.ui.TimerState
import com.example.androiddevchallenge.ui.theme.CountDownTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var alarmManager: AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        setContent {
            CountDownTheme {
                Surface(Modifier.background(MaterialTheme.colors.background)) {
                    CountDownApp(viewModel)
                }
            }
        }
        lifecycleScope.launch {
            viewModel.alertState.collect {
                if (it == AlertState.ZERO_DURATION) {
                    Toast.makeText(applicationContext, R.string.zero_duration, Toast.LENGTH_SHORT).show()
                }
            }
        }
        lifecycleScope.launch {
            viewModel.timerState.collect {
                when (it) {
                    is TimerState.Running -> {
                        startAlarm(viewModel.getMillisInFuture())
                    }
                    is TimerState.Idle -> {
                        cancelAlarm()
                    }
                    TimerState.Paused -> {
                        cancelAlarm()
                    }
                }
            }
        }
    }

    private fun startAlarm(timeRemainingInMillis: Long) {
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            1,
            Intent(this, AlertReceiver::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + timeRemainingInMillis, pendingIntent)
    }

    private fun cancelAlarm() {
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            1,
            Intent(this, AlertReceiver::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }
}
