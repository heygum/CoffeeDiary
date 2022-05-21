package com.heygum88.coffeediary.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * the diary data class
 */
@Entity
data class Diary(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: Long = Date().time,
    val body: String = "", // main diary body
)

