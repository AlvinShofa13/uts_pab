package com.example.utsapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface Item058Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem058(item058: Item058): Long

    @Update
    fun updateItem058(item058: Item058): Int

    @Delete
    fun deleteItem058(item058: Item058): Int

    @Query("SELECT * FROM item_058 WHERE userId_058 = :userId_058 ORDER BY id_058 DESC")
    fun getItemsByUserId058(userId_058: Int): LiveData<List<Item058>>

    @Query("SELECT * FROM item_058 WHERE id_058 = :id_058 LIMIT 1")
    fun getItemById058(id_058: Int): Item058?

    // Real-time calculation: Total inventory value = SUM(jumlah * harga) for the current user
    @Query("SELECT SUM(jumlah_058 * harga_058) FROM item_058 WHERE userId_058 = :userId_058")
    fun getTotalNilaiInventaris058(userId_058: Int): LiveData<Double?>

    // Total count of items for the current user
    @Query("SELECT COUNT(*) FROM item_058 WHERE userId_058 = :userId_058")
    fun getTotalJumlahItem058(userId_058: Int): LiveData<Int?>
}
