package com.hackheroes.healthybody.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class Constants {

    companion object {

        const val TAG = "AppDebug"

        const val BASE_URL = "https://bmi.p.rapidapi.com/"

        const val RUNNING_DATABASE_NAME = "running_db"

        const val REQUEST_CODE_LOCATION_PERMISSION = 0

        const val ACTION_START_OR_RESUME_SERVICE = "ACTION_START_OR_RESUME_SERVICE"
        const val ACTION_PAUSE_SERVICE = "ACTION_PAUSE_SERVICE"
        const val ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE"

        fun isNetworkConnected(context: Context) : Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities =  connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            capabilities.also {
                if (it != null){
                    if (it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                        return true
                    else if (it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                        return true
                    }
                }
            }
            return false
        }
    }

}