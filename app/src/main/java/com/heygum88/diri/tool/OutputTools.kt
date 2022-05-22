package com.heygum88.diri.tool

import android.os.Environment.DIRECTORY_DOWNLOADS
import android.os.Environment.getExternalStoragePublicDirectory
import android.util.Log
import com.heygum88.diri.data.Diary
import com.heygum88.diri.db.MDb
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.lang.StringBuilder
import java.util.*


/**
 * All Functions about output
 */
object OutputTools {

    val TAG = "OutputTools"

    fun outputAllDiaries(db: MDb){
        val diaryList: List<Diary> = db.DiaryDao().getAllNormal()
        val fileName = Date().time
        val mPath = getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).path + '/' + fileName;

        val data = fileFormat(diaryList)
        val file = File(mPath)
        val writeStream = FileOutputStream(file)
        try {
            writeStream.write(data.toByteArray())
        } catch (e:Exception){
            Log.d(TAG,e.toString())
        } finally {
            writeStream.close()
        }
    }

    /**
     * Default Output Format
     */
    fun fileFormat(list: List<Diary>):String{
        val sb = StringBuilder()

        for (diary in list){
            sb.append("//// ")
            sb.append(DateFormatter.millsToDate(diary.date))
            sb.append("\n")
            sb.append(diary.body)
            sb.append("\n\n")
        }
        return sb.toString()
    }

}