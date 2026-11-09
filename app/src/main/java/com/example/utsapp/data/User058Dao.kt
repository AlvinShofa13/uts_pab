package com.example.utsapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface User058Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser058(user058: User058): Long

    @Query("SELECT * FROM user_058 WHERE username_058 = :username_058 AND password_058 = :password_058 LIMIT 1")
    fun loginUser058(username_058: String, password_058: String): User058?

    @Query("SELECT COUNT(*) FROM user_058 WHERE username_058 = :username_058")
    fun checkUsernameExists058(username_058: String): Int

    @Query("SELECT * FROM user_058 WHERE id_058 = :id_058 LIMIT 1")
    fun getUser058ById(id_058: Int): User058?
}
