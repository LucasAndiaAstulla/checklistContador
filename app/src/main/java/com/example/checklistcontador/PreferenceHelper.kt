package com.example.checklistcontador

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("todo_prefs", Context.MODE_PRIVATE)

    fun saveCounter(title: String, value: Int) {
        prefs.edit().putInt("counter_$title", value).apply()
    }

    fun getCounter(title: String): Int {
        return prefs.getInt("counter_$title", 0)
    }
}
