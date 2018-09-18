package com.ryanburnsworth.kallerid.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_BOOT_COMPLETED

/*
    Listens for ACTION_BOOT_COMPLETED
    Starts the StartupService
 */
class StartupReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            ACTION_BOOT_COMPLETED -> startStartupService(context)
        }
    }

    private fun startStartupService(context: Context?) {
        val incomingCallReceiver = Intent(context, IncomingCallReceiver::class.java)
        context?.startService(incomingCallReceiver)
    }
}