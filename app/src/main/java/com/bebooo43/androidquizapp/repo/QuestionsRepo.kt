package com.bebooo43.androidquizapp.repo

import androidx.annotation.WorkerThread
import com.bebooo43.androidquizapp.MyApp
import com.bebooo43.androidquizapp.db.Question
import com.bebooo43.androidquizapp.db.QuestionDao
import com.bebooo43.androidquizapp.db.QuestionRoomDB

class QuestionsRepo(val app: MyApp) {

    private val dao: QuestionDao by lazy { QuestionRoomDB.getDatabase(app, app.applicationScope).questionDao() }

    val allQuestions = dao.getAllQuestions()

    @WorkerThread
    suspend fun add(question: Question){
        dao.addQuestion(question)
    }

    @WorkerThread
    suspend fun delete(question: Question){
        dao.deleteQuestion(question)
    }

    @WorkerThread
    suspend fun update(question: Question){
        dao.updateQuestion(question)
    }

}