package com.heygum88.diri.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heygum88.diri.data.User
import org.jetbrains.annotations.NotNull

/**
 * Link to User
 */
@Dao
interface UserDao {


    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT pw FROM user WHERE id = 1")
    suspend fun getPw(): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPw(user: User)
}