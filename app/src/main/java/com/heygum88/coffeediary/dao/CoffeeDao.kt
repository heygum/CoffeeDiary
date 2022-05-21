package com.heygum88.coffeediary.dao

import androidx.room.*
import com.heygum88.coffeediary.data.Coffee
import java.util.*

/**
 * Link database and Logic
 */
@Dao
interface CoffeeDao {
    @Query("SELECT * FROM coffee")
    suspend fun getAll(): List<Coffee>

    @Query("SELECT * FROM coffee WHERE id IN (:coffeeIds)")
    suspend fun loadAllByIds(coffeeIds: LongArray): List<Coffee>

    @Query("SELECT * FROM coffee WHERE date = :date")
    suspend fun getCoffeeByDate(date: Long): List<Coffee>

    @Query("SELECT * FROM coffee WHERE id = :coffeeId")
    suspend fun getCoffee(coffeeId: Long): Coffee

    @Insert
    suspend fun insertAll(vararg diaries: Coffee)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCoffee(coffee: Coffee)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun forceInsertCoffee(coffee: Coffee)

    @Delete
    suspend fun delete(coffee: Coffee)
}