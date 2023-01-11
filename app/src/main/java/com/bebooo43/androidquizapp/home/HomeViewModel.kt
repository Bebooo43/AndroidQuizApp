package com.bebooo43.androidquizapp.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.bebooo43.androidquizapp.MyApp
import com.bebooo43.androidquizapp.db.Question
import com.bebooo43.androidquizapp.extensions.toast
import com.bebooo43.androidquizapp.repo.QuestionsRepo

class HomeViewModel(private val app: Application): AndroidViewModel(app) {

    private val repo by lazy { QuestionsRepo(app as MyApp) }
    val questions: LiveData<List<Question>> by lazy { repo.allQuestions.asLiveData() }

    private val questionsModelList: MutableLiveData<MutableList<QuestionModel>> = MutableLiveData(arrayListOf())
    private var currentQuestionPosition = 0

    val currentQuestion = MutableLiveData<QuestionModel>()
    val isAChecked = MutableLiveData(false)
    val isBChecked = MutableLiveData(false)
    val isCChecked = MutableLiveData(false)
    val isDChecked = MutableLiveData(false)

    val navigateToResults = MutableLiveData<List<QuestionModel>>()

    fun startQuiz() {
        if (questionsModelList.value?.isEmpty() == true) {
            questions.value?.also {
                questionsModelList.value = mapToQuestionModel(it)
                currentQuestion.value = questionsModelList.value?.get(currentQuestionPosition)
                resetSelections()
            }
        }
    }

    fun onNextClicked() {
        getCheckedAnswer()?.also { answer ->
            questionsModelList.value?.also { questions ->
                questions[currentQuestionPosition].setSelectedAnswer(answer)
                moveToNextQuestionIfAvailable()
            }
        } ?: app.toast("Please select an answer")
    }

    fun skip() {
        moveToNextQuestionIfAvailable()
    }

    private fun moveToNextQuestionIfAvailable() {
        questionsModelList.value?.also { questions ->
            if (currentQuestionPosition < questions.size - 1) {
                showNextQuestion(questions)
            } else {
                endQuiz()
            }
        }
    }

    private fun mapToQuestionModel(list: List<Question>) =
        list.shuffled().mapIndexed { index, it ->
            QuestionModel(
                "${index+1}) " + it.questionText,
                it.a,
                it.b,
                it.c,
                it.d,
                it.correctAnswer,
                it.explanation
            )
        }.toMutableList()


    private fun showNextQuestion(questions: List<QuestionModel>) {
        currentQuestionPosition += 1
        currentQuestion.value = questions[currentQuestionPosition]
        resetSelections()
    }

    private fun resetSelections() {
        isAChecked.value = false
        isBChecked.value = false
        isCChecked.value = false
        isDChecked.value = false
    }

    private fun endQuiz() {
        navigateToResults.value = questionsModelList.value
        questionsModelList.value = mutableListOf()
    }

    private fun getCheckedAnswer(): Answer? = when {
        isAChecked.value == true -> Answer.A
        isBChecked.value == true -> Answer.B
        isCChecked.value == true -> Answer.C
        isDChecked.value == true -> Answer.D
        else -> null
    }

}