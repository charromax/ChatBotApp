package com.example.chatbotapp.utils

import android.view.View
import android.widget.TextView
import com.example.chatbotapp.data.Message

fun TextView.showMessage(msg: Message) {
    text = msg.message
    visibility = View.VISIBLE
}