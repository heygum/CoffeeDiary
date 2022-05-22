package com.heygum88.coffeediary.tool

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {
    private val dateFormat = "yyyy/MM/dd/hh/mm/ss"
    @SuppressLint("SimpleDateFormat")
    private val formatter = SimpleDateFormat(dateFormat)

    fun millsToDate(mills: Long): String {
        return formatter.format(mills)?:""
    }
}