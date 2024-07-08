package com.example.travelookup

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.io.path.Path
import java.io.BufferedReader
import java.io.InputStreamReader

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


        object DebuggingIdentifiers {
            val actionOrEventSucceded: String = "✅"
            val actionOrEventInProgress: String = "⚈ ⚈ ⚈"
            val actionOrEventFailed: String = "❌"
            val notificationSent: String = "📤"
            val notificationRecieved: String = "📥"
        }

        fun readJSONFromAssets(context: Context, path: String): String {
            val identifier = "[ReadJSON]"
            try {
                val file = context.assets.open("$path")
                Log.i(
                    identifier,
                    "${DebuggingIdentifiers.actionOrEventSucceded} Found File: $file.",
                )
                val bufferedReader = BufferedReader(InputStreamReader(file))
                val stringBuilder = StringBuilder()
                bufferedReader.useLines { lines ->
                    lines.forEach {
                        stringBuilder.append(it)
                    }
                }
                Log.i(
                    identifier,
                    "getJSON  ${DebuggingIdentifiers.actionOrEventSucceded} stringBuilder: $stringBuilder.",
                )
                val jsonString = stringBuilder.toString()
                Log.i(
                    identifier,
                    "${DebuggingIdentifiers.actionOrEventSucceded} JSON as String: $jsonString.",
                )
                return jsonString
            } catch (e: Exception) {
                Log.e(
                    identifier,
                    "${DebuggingIdentifiers.actionOrEventFailed} Error reading JSON: $e.",
                )
                e.printStackTrace()
                return ""
            }
        }

    }
}