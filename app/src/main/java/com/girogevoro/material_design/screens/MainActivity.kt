package com.girogevoro.material_design.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.girogevoro.material_design.R
import com.girogevoro.material_design.databinding.ActivityMainBinding

const val ThemeBlue = 1
const val ThemeGreen = 2
const val ThemeYellow = 3

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getRealStyle(getCurrentTheme()))
        _binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)
        initBottomNavigationView()

//       if(savedInstanceState == null) {
//           binding.bottomNavigationView.selectedItemId = R.id.marsFragment
//      }
    }

    private fun initBottomNavigationView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

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
