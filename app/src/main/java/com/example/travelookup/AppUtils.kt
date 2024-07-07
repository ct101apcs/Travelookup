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
        val FIRST_NAME_KEY = "first_name"
        val LAST_NAME_KEY = "last_name"
        val PHONE_KEY = "phone"
        val EMAIL_KEY = "email"
        val PROFILE_AVATAR = "profile_avatar"
        val PRIVATE_MODE = 0
        val FIRST_RUN_KEY = "firstrun"
    }
}