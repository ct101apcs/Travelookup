package com.example.travelookup

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar

class AppUtils {
    companion object {
        fun getCurrentDate(): String? {
            val c = Calendar.getInstance().time
            val df = SimpleDateFormat("dd-MM-yyyy")
            return df.format(c)
        }

        val USERS_SHARED_PREF = "user_pref"
        val PRIVATE_MODE = 0
        val FIRST_RUN_KEY = "firstrun"
    }
}