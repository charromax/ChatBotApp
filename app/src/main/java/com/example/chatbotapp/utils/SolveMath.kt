package com.example.chatbotapp.utils

object SolveMath {

    fun solveMath(equation: String): Int {
        val newEquation = equation.replace(" ", "")

        return when {
            newEquation.contains("+") -> {
                val split = newEquation.split("+")
                return (split[0].toInt() + split[1].toInt())
            }
            newEquation.contains("-") -> {
                val split = newEquation.split("-")
                return (split[0].toInt() - split[1].toInt())
            }
            newEquation.contains("*") -> {
                val split = newEquation.split("*")
                return (split[0].toInt() * split[1].toInt())
            }
            newEquation.contains("/") -> {
                val split = newEquation.split("/")
                return (split[0].toInt() / split[1].toInt())
            }
            else -> 0
        }
    }

}