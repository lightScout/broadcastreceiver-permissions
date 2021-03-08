package com.britshbroadcast.broadcastreceiverpermissions.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import com.britshbroadcast.broadcastreceiverpermissions.util.Logger.Companion.logDebug
import com.britshbroadcast.broadcastreceiverpermissions.util.Logger.Companion.logError

class AirPlaneModeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action

        if(action == "android.intent.action.AIRPLANE_MODE"){
            logDebug("Airplane Mode Changed")
            val on: Boolean = getModeState(context)

            val state = if(on) "ON" else "OFF"

            Toast.makeText(context,"Airplane Mode Changed to $state", Toast.LENGTH_LONG).show()


        } else{
            logError("Not Airplane Mode.")
        }
    }

    private fun getModeState(context: Context): Boolean  = Settings.System.getInt(context.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0
}