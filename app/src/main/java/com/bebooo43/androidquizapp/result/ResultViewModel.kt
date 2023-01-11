package com.bebooo43.androidquizapp.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bebooo43.androidquizapp.home.QuestionModel

class ResultViewModel: ViewModel() {

    lateinit var answersList: List<QuestionModel>
    val resultText = MutableLiveData("")

    fun setResults(list: List<QuestionModel>) {
        answersList = list
        val score = answersList.filter { it.answer == it.correctAnswer }.size
        val wrongAnswers = answersList.size - score
        val finalScore = "$score/${answersList.size}"

        resultText.value = String.format(
            "Total Questions: %d\n\nCorrect Answers(Score): %d\n\nWrong Answers: %d\n\nYour Score is: %s",
            answersList.size ,score, wrongAnswers, finalScore
        )
    }

}