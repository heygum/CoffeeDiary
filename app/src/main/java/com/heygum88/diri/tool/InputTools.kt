package com.heygum88.diri.tool

import android.util.Log
import com.heygum88.diri.data.Diary
import com.heygum88.diri.db.MDb
import java.io.InputStream
import java.lang.Exception
import java.lang.StringBuilder
import java.text.SimpleDateFormat

/**
 * All about input
 */
object InputTools {
    const val TAG = "InputTools"

    /**
     * just for ////date \n body format diary
     */
    fun inputSpecificDiary(inputStream: InputStream?,db:MDb): Boolean{
        try {
            val list = inputStream?.reader()?.readLines()
            if (list != null) {
                return appendToDatabase(list,db)
            }
            return false
        } catch (e: Exception) {
            Log.d(TAG,e.toString())
            return false
        }
    }

    private fun appendToDatabase(list: List<String>,db:MDb): Boolean{

        val formatter = SimpleDateFormat("yyyy/MM/dd/hh/mm")
        var dates: List<String> = emptyList()
        val sb = StringBuilder()
        for(line in list){
            if(line.contains("////")){
                if(sb.isNotEmpty() && dates.isNotEmpty()) {
                    val diary = Diary(date = formatter.parse(dates[0] + '/' + dates[1] + '/' + dates[2] + '/' + dates[3] + '/' + dates[4]).time
                    ,body = sb.toString())
                    db.DiaryDao().insertDiary(diary)
                    sb.clear()
                    dates = emptyList()
                }
                dates = line.trim().replace("////","").split("/")
                if (dates.size < 3) {
                    dates = emptyList()
                }
            } else {
                sb.append(line)
                sb.append('\n')
            }
        }
        if(dates.isEmpty()) {
            return false
        }
        return true
    }
}