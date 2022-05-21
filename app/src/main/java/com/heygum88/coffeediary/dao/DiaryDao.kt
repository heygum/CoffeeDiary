package com.heygum88.coffeediary.dao

import androidx.room.*
import com.heygum88.coffeediary.data.Coffee
import com.heygum88.coffeediary.data.Diary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import java.util.*

/**
 * Link database and logic
 */
@Dao
interface DiaryDao {
    @Query("SELECT * FROM diary")
    fun getAll(): Flow<List<Diary>>

    @Query("SELECT * FROM diary WHERE id IN (:diaryIds)")
    suspend fun loadAllByIds(diaryIds: LongArray): List<Diary>

    @Query("SELECT * FROM diary WHERE date = :date")
    suspend fun getDiaryByDate(date: Long): List<Diary>

    @Query("SELECT * FROM diary WHERE id = :diaryId")
    suspend fun getDiary(diaryId: Long): Diary

    @Insert
    suspend fun insertAll(vararg diaries: Diary)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDiary(diary: Diary)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun forceInsertDiary(diary: Diary)

    @Delete
    suspend fun delete(diary: Diary)
}