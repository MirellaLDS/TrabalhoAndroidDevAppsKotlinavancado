package com.example.mirella.trabalho_android_dev_apps_kotlin_avancado

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Toast.makeText(context, "Static Broadcast", Toast.LENGTH_SHORT).show()

        NotificationUtil(context).notifyUtil()
    }
}