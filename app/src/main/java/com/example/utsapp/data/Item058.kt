package com.example.utsapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "item_058",
    foreignKeys = [
        ForeignKey(
            entity = User058::class,
            parentColumns = ["id_058"],
            childColumns = ["userId_058"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [Index(value = ["userId_058"])],
)
data class Item058(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_058")
    val id_058: Int = 0,

    @ColumnInfo(name = "userId_058")
    val userId_058: Int,

    @ColumnInfo(name = "nama_058")
    val nama_058: String,

    @ColumnInfo(name = "jumlah_058")
    val jumlah_058: Int,

    @ColumnInfo(name = "harga_058")
    val harga_058: Double,
)
