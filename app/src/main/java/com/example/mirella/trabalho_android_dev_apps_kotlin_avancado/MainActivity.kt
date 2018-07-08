package com.example.mirella.trabalho_android_dev_apps_kotlin_avancado

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mensagem = getNotificationExtra()

        val intent = Intent(this, MyIntentService::class.java)
        intent.putExtra(NOTIFICATION_EXTRA_UTIL, mensagem)
        startService(intent)

        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
                mutableListOf("item 1", "Item 2", "Item 3"))

        list.adapter = adapter

    }

    private fun getNotificationExtra() : String {
        val intentExtras = intent.extras

        val title = if (intentExtras != null) {
            intentExtras.getString("message")
        } else {
            ""
        }
        Toast.makeText(baseContext, title, Toast.LENGTH_LONG).show()
        return title
    }

    companion object {
        const val NOTIFICATION_EXTRA_UTIL = "NOTIFICATION_EXTRA_UTIL"
        const val CHANNEL_ID = "samples.NotificationUtil.devdeeds.com.CHANNEL_ID"
        const val CHANNEL_NAME = "Sample Notification"
    }

}
