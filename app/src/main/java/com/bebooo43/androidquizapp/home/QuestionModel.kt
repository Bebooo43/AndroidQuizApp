package com.bebooo43.androidquizapp.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionModel(
    val questionText: String,
    val a: String,
    val b: String,
    val c: String,
    val d: String,
    val correctAnswer: String,
    val explanation: String,
    var answer: String = ""
) : Parcelable {

    fun setSelectedAnswer(selectedAnswer: Answer) {
        answer = selectedAnswer.value
    }
}

enum class Answer(val value: String) { A("a"), B("b"), C("c"), D("d") }