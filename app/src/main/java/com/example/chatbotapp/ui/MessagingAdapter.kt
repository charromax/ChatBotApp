package com.example.chatbotapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbotapp.R
import com.example.chatbotapp.data.Message
import com.example.chatbotapp.utils.Constants.RECEIVE_ID
import com.example.chatbotapp.utils.Constants.SEND_ID
import com.example.chatbotapp.utils.showMessage
import kotlinx.android.synthetic.main.message_item.view.*

class MessagingAdapter: RecyclerView.Adapter<MessagingAdapter.MessagingViewHolder>() {

    var messagesList = mutableListOf<Message>()
    inner class MessagingViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener{
                messagesList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagingViewHolder {
        return MessagingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false))
    }

    override fun onBindViewHolder(holder: MessagingViewHolder, position: Int) {
        val currentMessage = messagesList[position]
        when (currentMessage.id) {
            SEND_ID -> {
                holder.itemView.tv_message.showMessage(currentMessage)
                holder.itemView.tv_bot_message.visibility = View.GONE
            }
            RECEIVE_ID -> {
                holder.itemView.tv_bot_message.showMessage(currentMessage)
                holder.itemView.tv_message.visibility = View.GONE
            }
        }
    }

    override fun getItemCount() = messagesList.size

    fun insertMessage(message: Message) {
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
    }
}