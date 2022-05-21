package com.heygum88.coffeediary.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.heygum88.coffeediary.dao.CoffeeDao
import com.heygum88.coffeediary.dao.DiaryDao
import com.heygum88.coffeediary.data.Coffee
import com.heygum88.coffeediary.data.Diary


const val DB_VERSION = 1

/**
 * DataBase
 */
@Database(
    version = DB_VERSION,
    entities = [
        Diary::class,
        Coffee::class
    ])
abstract class MDb: RoomDatabase() {
    abstract fun DiaryDao(): DiaryDao
    abstract fun CoffeeDao(): CoffeeDao

    companion object {
        @JvmField
        val DB_NAME = "diary.db"
        private var INSTANCE: MDb? = null

        fun getInstance(context: Context): MDb {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                    MDb::class.java, DB_NAME).allowMainThreadQueries().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}