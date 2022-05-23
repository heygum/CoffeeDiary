package com.heygum88.diri.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * All about User
 */
@Entity
data class User (
    @PrimaryKey
    val id: Long = 1,
    val pw: String = ""
)