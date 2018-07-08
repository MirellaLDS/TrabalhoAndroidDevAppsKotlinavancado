package com.example.mirella.trabalho_android_dev_apps_kotlin_avancado

import android.app.IntentService
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log


class MyIntentService : IntentService("MyIntentService") {

    private val mBinder = LocalBinder()
    var message = ""

    override fun onHandleIntent(intent: Intent?) {

//        Log.i(">>> Mirella", "Teste")

        val intentExtras = intent!!.extras
        if (intentExtras != null) {
            val title = intentExtras.getString(MainActivity.NOTIFICATION_EXTRA_UTIL)
            message = title
//            Log.i(">>> Mirella", title)
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    inner class LocalBinder : Binder() {
        fun getService(): MyIntentService {
            return this@MyIntentService
        }
    }

    fun getMessageNotification(): String {
        return message
    }
}
