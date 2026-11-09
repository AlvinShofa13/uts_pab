package com.example.utsapp.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InventarisRepository058(db058: AppDatabase058) {

    private val user058Dao = db058.user058Dao()
    private val item058Dao = db058.item058Dao()

    suspend fun registerUser058(username_058: String, password_058: String): Long =
        withContext(Dispatchers.IO) {
            val exists = user058Dao.checkUsernameExists058(username_058)
            if (exists > 0) {
                -1L
            } else {
                user058Dao.insertUser058(User058(username_058 = username_058, password_058 = password_058))
            }
        }

    suspend fun loginUser058(username_058: String, password_058: String): User058? =
        withContext(Dispatchers.IO) {
            user058Dao.loginUser058(username_058, password_058)
        }

    fun getItemsByUserId058(userId_058: Int): LiveData<List<Item058>> {
        return item058Dao.getItemsByUserId058(userId_058)
    }

    fun getTotalNilaiInventaris058(userId_058: Int): LiveData<Double?> {
        return item058Dao.getTotalNilaiInventaris058(userId_058)
    }

    fun getTotalJumlahItem058(userId_058: Int): LiveData<Int?> {
        return item058Dao.getTotalJumlahItem058(userId_058)
    }

    suspend fun insertItem058(item058: Item058): Long =
        withContext(Dispatchers.IO) {
            item058Dao.insertItem058(item058)
        }

    suspend fun updateItem058(item058: Item058): Int =
        withContext(Dispatchers.IO) {
            item058Dao.updateItem058(item058)
        }

    suspend fun deleteItem058(item058: Item058): Int =
        withContext(Dispatchers.IO) {
            item058Dao.deleteItem058(item058)
        }

    suspend fun getItemById058(id_058: Int): Item058? =
        withContext(Dispatchers.IO) {
            item058Dao.getItemById058(id_058)
        }
}
