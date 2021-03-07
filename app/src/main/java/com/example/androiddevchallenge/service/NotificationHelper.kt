package com.example.androiddevchallenge.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import android.annotation.TargetApi
import android.content.ContentResolver
import android.content.Context
import android.content.ContextWrapper
import android.media.AudioAttributes
import android.net.Uri
import androidx.core.app.NotificationManagerCompat
import com.example.androiddevchallenge.R

class NotificationHelper(context: Context) : ContextWrapper(context) {
    companion object {
        const val channelID = "channelID"
        const val channelName = "Channel Name"
    }

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private val channelNotification: NotificationCompat.Builder
        get() = NotificationCompat.Builder(applicationContext, channelID)
            .setContentTitle("Alarm!")
            .setSmallIcon(R.drawable.ic_app_icon)

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelID,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                val soundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + applicationContext.packageName + "/" + R.raw.alarm_sound)
                setSound(
                    soundUri,
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                        .build()
                )
                enableLights(true)
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun startNotifying() {
        notificationManager.notify(1, channelNotification.build())
    }
}
