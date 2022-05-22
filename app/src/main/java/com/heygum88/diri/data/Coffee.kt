package com.heygum88.diri.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * the coffee data class
 */
@Entity
data class Coffee(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: Long = Date().time,
    val duration: Long = 0,  // Record as ms
    val waterTemperature: Float = 0F,
    val waterVolume: Float = 0F,
    val method: String = "", // Extraction method
    val waterBrand: String = "", // can be default
    val beanGram: Float = 0F,
    val beanBrand: String = "", // where to buy
    val beanSource: String = "", // the country or something else
    val result: String = "" // the description
)