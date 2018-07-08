package com.example.mirella.trabalho_android_dev_apps_kotlin_avancado

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
                mutableListOf("item 1", "Item 2", "Item 3"))

        list.adapter = adapter

    }


    override fun onStart() {
        super.onStart()
        val intent = Intent(this, MyIntentService::class.java)
        startService(intent)
    }

    companion object {

        const val CHANNEL_ID = "samples.NotificationUtil.devdeeds.com.CHANNEL_ID"
        const val CHANNEL_NAME = "Sample Notification"
    }

}
