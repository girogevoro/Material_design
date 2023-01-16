package com.girogevoro.material_design.screens

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.girogevoro.material_design.R

const val ThemeBlue = 1
const val ThemeGreen = 2
const val ThemeYellow = 3

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getRealStyle(getCurrentTheme()))
        setContentView(R.layout.activity_main)
    }

    private val KEY_SP = "sp"
    private val KEY_CURRENT_THEME = "current_theme"

    fun setCurrentTheme(currentTheme: Int) {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_CURRENT_THEME, currentTheme)
        editor.apply()
    }

    fun getCurrentTheme(): Int {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return sharedPreferences.getInt(KEY_CURRENT_THEME, ThemeYellow)
    }

    private fun getRealStyle(currentTheme: Int): Int {
        return when (currentTheme) {
            ThemeBlue -> R.style.AppThemeBlue
            ThemeGreen -> R.style.AppThemeGreen
            ThemeYellow -> R.style.AppThemeYellow
            else -> 1
        }
    }


}
