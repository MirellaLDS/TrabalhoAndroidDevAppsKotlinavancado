package com.example.mirella.trabalho_android_dev_apps_kotlin_avancado

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var pIntent: PendingIntent
    private lateinit var alarme: AlarmManager

    private lateinit var myLocal: MyIntentService
    private var mBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setAlarm()

        var mensagem = getNotificationExtra()

        val intent = Intent(this, MyIntentService::class.java)
        intent.putExtra(NOTIFICATION_EXTRA_UTIL, mensagem)
        startService(intent)

        var data = mutableListOf("item 1", "Item 2", "Item 3")
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
                data)

        list.adapter = adapter

        getNotificationBtn.setOnClickListener {
            var text = myLocal.getMessageNotification()
            data.add(text)
            adapter.notifyDataSetChanged()
        }

    }

    private fun setAlarm() {
        val intent = Intent("MY_BROADCAST_STATIC")
        pIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarme = getSystemService(ALARM_SERVICE) as AlarmManager
        alarme.setRepeating(AlarmManager.RTC_WAKEUP, 1, 60, pIntent)
    }

    private fun getNotificationExtra() : String {
        val intentExtras = intent.extras

        return if (intentExtras != null) {
            intentExtras.getString("message")
        } else {
            ""
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        alarme.cancel(pIntent)
    }

    override fun onStart() {
        super.onStart()
val intent = Intent(this, MyIntentService::class.java)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (mBound) {
            unbindService(mConnection)
            mBound = false
        }
    }

    private val mConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val localBinder = service as MyIntentService.LocalBinder
            myLocal = localBinder.getService()
            mBound = true
        }
    }

    companion object {
        const val NOTIFICATION_EXTRA_UTIL = "NOTIFICATION_EXTRA_UTIL"
        const val CHANNEL_ID = "samples.NotificationUtil.devdeeds.com.CHANNEL_ID"
        const val CHANNEL_NAME = "Sample Notification"
    }

}
