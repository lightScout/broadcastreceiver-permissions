package com.britshbroadcast.broadcastreceiverpermissions.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.britshbroadcast.broadcastreceiverpermissions.R
import com.britshbroadcast.broadcastreceiverpermissions.data.WeatherData
import com.britshbroadcast.broadcastreceiverpermissions.receiver.AirPlaneModeReceiver
import com.britshbroadcast.broadcastreceiverpermissions.util.Logger.Companion.logDebug
import com.britshbroadcast.broadcastreceiverpermissions.util.Weather
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private val airPlaneModeReceiver = AirPlaneModeReceiver()

    private val CUSTOM_FILTER = "106.8"
    private val customReceiver = object: BroadcastReceiver(){
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action

            if(action == "106.8"){
                val weatherCondition = intent.getParcelableExtra<WeatherData>(CUSTOM_FILTER)
                logDebug(weatherCondition.toString())
            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver(customReceiver, IntentFilter(CUSTOM_FILTER))

        sendBroadcast(Intent(CUSTOM_FILTER).also {
            it.putExtra(CUSTOM_FILTER, WeatherData("08/03/2021", Weather.SUNNY))
        })


        //Requesting Runtime permissions
        //1st step - Check if permission is enabled
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            //Permission is already granted = continue application flow

        }else{
            //2nd step: If permission is denied, request it
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 707)
        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permissions granted", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Permissions not granted", Toast.LENGTH_SHORT).show()
            }
        }

    }



    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(customReceiver)
    }
}