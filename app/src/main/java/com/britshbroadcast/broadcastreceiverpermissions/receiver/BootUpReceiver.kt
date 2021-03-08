package com.britshbroadcast.broadcastreceiverpermissions.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.britshbroadcast.broadcastreceiverpermissions.util.Logger.Companion.logDebug
import com.britshbroadcast.broadcastreceiverpermissions.util.Logger.Companion.logError

class BootUpReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action

        if(action == "android.intent.action.BOOT_COMPLETED") {
            logDebug("Boot up completed!")

            Toast.makeText(context, "Boot up completed", Toast.LENGTH_SHORT).show()
        }else{
            logError("Boot up no completed")
        }
    }
}