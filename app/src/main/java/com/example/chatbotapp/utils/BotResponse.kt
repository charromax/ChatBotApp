package com.example.chatbotapp.utils

import com.example.chatbotapp.utils.Constants.OPEN_GOOGLE
import com.example.chatbotapp.utils.Constants.OPEN_SEARCH
import com.example.chatbotapp.utils.SolveMath.solveMath

object BotResponse {

    fun basicResponses(_message: String): String {
        val random = (0..2).random()
        val message = _message.toLowerCase()

        return when {
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Hi, how are you?"
                    2 -> "Salutations, how do you do?"
                    else -> "Sorry, I didn't get that, please repeat"
                }

            }
            message.contains("how are you") || message.contains("how do you do") || message.contains("how r u")-> {
                when (random) {
                    0 -> "Doing fine, thank you"
                    1 -> "I'm good, thank you for asking"
                    2 -> "Can't complain, how about you?"
                    else -> "Sorry, I didn't get that, please repeat"
                }

            }
            (message.contains("good") || message.contains("great") || message.contains("fine"))
                    && (message.contains("thanks") || message.contains("thank"))   -> {
                when (random) {
                    0 -> "That's good to hear"
                    1 -> "Great!"
                    2 -> "Nice"
                    else -> "Sorry, I didn't get that, please repeat"
                }

            }
            message.contains("answer") && message.contains("life")
                    && message.contains("universe") && message.contains("everything") -> {
                return 42.toString()
            }
            message.contains("flip") && message.contains("coin") -> {
                var r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"
                return "I flipped the coin and it landed on $result"
            }

            message.contains("time") && message.contains("?") -> Time.timeStamp()

            message.contains("solve") -> {
                val eq: String?= message.substringAfter("solve")

                return try {
                    val answer = solveMath(eq ?: "0")
                    return answer.toString()
                } catch (e: Exception) {
                    e.printStackTrace()
                    return "Sorry I can't solve that!"
                }
            }

            message.contains("open") && message.contains("google") -> OPEN_GOOGLE

            message.contains("search") -> OPEN_SEARCH

            else -> when (random) {
                0 -> "Say again, please?"
                1 -> "Are you toying with me, son?"
                2 -> "Say again please?"
                else -> "Sorry, I didn't get that, please repeat"
            }
        }
    }

}