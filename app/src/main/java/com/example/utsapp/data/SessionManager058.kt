package com.example.utsapp.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SessionManager058(context: Context) {

    private val sharedPreferences058: SharedPreferences =
        context.getSharedPreferences(PREF_NAME_058, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME_058 = "session_pref_058"
        private const val KEY_IS_LOGGED_IN_058 = "key_is_logged_in_058"
        private const val KEY_USER_ID_058 = "key_user_id_058"
        private const val KEY_USERNAME_058 = "key_username_058"
    }

    fun saveSession058(userId_058: Int, username_058: String) {
        sharedPreferences058.edit {
            putBoolean(KEY_IS_LOGGED_IN_058, true)
            putInt(KEY_USER_ID_058, userId_058)
            putString(KEY_USERNAME_058, username_058)
        }
    }

    fun isLoggedIn058(): Boolean {
        return sharedPreferences058.getBoolean(KEY_IS_LOGGED_IN_058, false)
    }

    fun getUserId058(): Int {
        return sharedPreferences058.getInt(KEY_USER_ID_058, -1)
    }

    fun getUsername058(): String {
        return sharedPreferences058.getString(KEY_USERNAME_058, "") ?: ""
    }

    fun clearSession058() {
        sharedPreferences058.edit {
            clear()
        }
    }
}
