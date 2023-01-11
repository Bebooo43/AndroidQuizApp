package com.bebooo43.androidquizapp.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "quiz")
data class Question(
    @PrimaryKey
    val id: Long,
    val questionText: String,
    val a: String,
    val b: String,
    val c: String,
    val d: String,
    val correctAnswer: String,
    val explanation: String
) : Parcelable
