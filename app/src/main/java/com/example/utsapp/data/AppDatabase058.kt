package com.example.utsapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [User058::class, Item058::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase058 : RoomDatabase() {

    abstract fun user058Dao(): User058Dao
    abstract fun item058Dao(): Item058Dao

    companion object {
        @Volatile
        private var INSTANCE_058: AppDatabase058? = null

        fun getDatabase058(context: Context): AppDatabase058 {
            return INSTANCE_058 ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase058::class.java,
                    "inventaris_db_058",
                ).build()
                INSTANCE_058 = instance
                instance
            }
        }
    }
}
