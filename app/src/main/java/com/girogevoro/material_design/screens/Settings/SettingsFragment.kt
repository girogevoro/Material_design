package com.girogevoro.material_design.screens.Settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.girogevoro.material_design.databinding.FragmentSettingsBinding
import com.girogevoro.material_design.screens.MainActivity
import com.girogevoro.material_design.screens.ThemeBlue
import com.girogevoro.material_design.screens.ThemeGreen
import com.girogevoro.material_design.screens.ThemeYellow


/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var parentActivity: MainActivity // 1 способ получить родительскую активити
    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity = requireActivity() as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBlue.setOnClickListener{
            parentActivity.setCurrentTheme(ThemeBlue)
            parentActivity.recreate()
        }

        binding.buttonGreen.setOnClickListener{
            parentActivity.setCurrentTheme(ThemeGreen)
            parentActivity.recreate()
        }

        binding.buttonYellow.setOnClickListener{
            parentActivity.setCurrentTheme(ThemeYellow)
            parentActivity.recreate()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SettingsFragment()
    }
}