package com.example.androiddevchallenge.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlertReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        NotificationHelper(context).startNotifying()
    }
}
