package com.ryanburnsworth.kallerid.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import com.ryanburnsworth.kallerid.util.NetworkCheck
import com.ryanburnsworth.kallerid.view.MainActivity

class IncomingCallReceiver : BroadcastReceiver() {
    private var context: Context? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        this.context = context
        val stateStr = intent?.extras?.getString(TelephonyManager.EXTRA_STATE).toString()
        val number = intent?.extras?.getString(TelephonyManager.EXTRA_INCOMING_NUMBER).toString()
        if (stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING))
            startCallerIdActivity(number)
    }

    private fun startCallerIdActivity(number: String) {
        val networkCheck = NetworkCheck()
        if (networkCheck.checkNetworkState(context))
            return

        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra("PHONENUMBER", number)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context?.startActivity(intent)
    }
}