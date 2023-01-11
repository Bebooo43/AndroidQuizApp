package com.bebooo43.androidquizapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuestion(question: Question)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuestions(vararg question: Question)

    @Query("SELECT * FROM quiz ORDER BY id")
    fun getAllQuestions(): Flow<MutableList<Question>>

    @Delete
    suspend fun deleteQuestion(question: Question)

    @Update
    suspend fun updateQuestion(question: Question)

}