package com.hackheroes.healthybody.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class Constants {

    companion object {

        const val TAG = "AppDebug"

        const val BASE_URL = "https://bmi.p.rapidapi.com/"

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