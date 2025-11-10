package com.bangkok.app.data

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    
    companion object {
        private const val PREFS_NAME = "bangkok_session"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
    
    fun saveUserId(userId: String) {
        prefs.edit().putString(KEY_USER_ID, userId).apply()
        prefs.edit().putBoolean(KEY_IS_LOGGED_IN, true).apply()
    }
    
    fun getUserId(): String? {
        return prefs.getString(KEY_USER_ID, null)
    }
    
    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false) && getUserId() != null
    }
    
    fun clearSession() {
        prefs.edit().clear().apply()
    }
    
    fun logout() {
        clearSession()
    }
}

