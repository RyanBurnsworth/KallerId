package com.ryanburnsworth.kallerid.service

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.support.v4.content.LocalBroadcastManager
import com.ryanburnsworth.kallerid.receivers.IncomingCallReceiver
/*
    This service is fired on bootup by StartupReceiver
    This service will start the IncomingCallReceiver
 */

class StartupService : Service() {
    private val incomingCallReceiver = IncomingCallReceiver()

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        startIncomingCallReceiver()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopIncomingCallReceiver()
    }

    private fun startIncomingCallReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.priority=999

        LocalBroadcastManager.getInstance(applicationContext).registerReceiver(incomingCallReceiver, intentFilter)
    }

    private fun stopIncomingCallReceiver() {
        LocalBroadcastManager.getInstance(applicationContext).unregisterReceiver(incomingCallReceiver)
    }
}
