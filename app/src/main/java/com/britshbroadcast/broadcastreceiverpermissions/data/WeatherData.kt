package com.britshbroadcast.broadcastreceiverpermissions.data

import android.os.Parcelable
import com.britshbroadcast.broadcastreceiverpermissions.util.Weather
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherData(var day: String, var weather: Weather): Parcelable