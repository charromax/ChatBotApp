package com.example.chatbotapp.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat

object Time {

    fun timeStamp() : String {
        val timeStamp = Timestamp(System.currentTimeMillis())
        val sdf = SimpleDateFormat("HH:mm")
        return sdf.format(timeStamp.time).toString()
    }
}