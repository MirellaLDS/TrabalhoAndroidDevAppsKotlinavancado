package com.example.mirella.trabalho_android_dev_apps_kotlin_avancado

import android.app.IntentService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import android.os.Bundle
import android.widget.Toast


class MyIntentService : IntentService("MyIntentService") {
    override fun onHandleIntent(intent: Intent?) {

        intent?.let {
            val intentExtras = it.extras
            if (intentExtras != null) {
                val title = intentExtras.getString(MainActivity.NOTIFICATION_EXTRA_UTIL)
                Log.i(">>> Mirella", title)
            }
        }

    }

}
