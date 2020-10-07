package com.example.chatbotapp.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatbotapp.R
import com.example.chatbotapp.data.Message
import com.example.chatbotapp.utils.BotResponse
import com.example.chatbotapp.utils.Constants.OPEN_GOOGLE
import com.example.chatbotapp.utils.Constants.OPEN_SEARCH
import com.example.chatbotapp.utils.Constants.RECEIVE_ID
import com.example.chatbotapp.utils.Constants.SEND_ID
import com.example.chatbotapp.utils.Time
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MessagingAdapter
    private val botList = listOf("Brendon", "Jack", "Clare", "Yvette", "Owen")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()

        registerClickEvents()

        val random = (0..4).random()
        setWelcomeMessage("Hi, you're speaking with ${botList[random]}! How may I assist you today?")


    }

    private fun registerClickEvents() {
        btn_send.setOnClickListener {
            sendMessage()
        }
        et_message.setOnClickListener{
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }
    }

    private fun setRecyclerView() {
        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun setWelcomeMessage(message: String) {
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timestamp = Time.timeStamp()
                adapter.insertMessage(Message(message, RECEIVE_ID, timestamp))
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
    private fun sendMessage() {
        val message = et_message.text.toString()
        val timestamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            et_message.setText("")
            adapter.insertMessage(Message(message, SEND_ID, timestamp))
            rv_messages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        val response = BotResponse.basicResponses(message)
        val timestamp = Time.timeStamp()
        adapter.insertMessage(Message(response, RECEIVE_ID, timestamp))
        rv_messages.scrollToPosition(adapter.itemCount - 1)
        when (response) {
            OPEN_GOOGLE -> {
                val site = Intent(Intent.ACTION_VIEW)
                site.data = Uri.parse("https://google.com/")
                startActivity(site)
            }
            OPEN_SEARCH -> {
                val search = Intent(Intent.ACTION_VIEW)
                val searchTerms = message.toLowerCase().substringAfter("search")
                search.data = Uri.parse("https://google.com/search?&q=$searchTerms")
                startActivity(search)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
}