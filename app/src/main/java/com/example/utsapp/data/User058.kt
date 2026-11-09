package com.example.utsapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_058")
data class User058(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_058")
    val id_058: Int = 0,

    @ColumnInfo(name = "username_058")
    val username_058: String,

    @ColumnInfo(name = "password_058")
    val password_058: String,
)
