package com.girogevoro.material_design.screens.earth.PictureOfTheDay

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.girogevoro.material_design.R
import com.girogevoro.material_design.databinding.FragmentPictureOfTheDayBinding
import com.girogevoro.material_design.screens.MainActivity
import com.girogevoro.material_design.util.DEBUG
import com.girogevoro.material_design.util.TAG
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior

private const val DAY_AGO = "day_ago"

class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private var dayAgo:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.let {
             dayAgo = it.getInt(DAY_AGO)
        }
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root

    }

    private val viewModel by lazy {
        ViewModelProvider(this)[PictureOfTheDayViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMenu()

        viewModel.getLiveData().observe(viewLifecycleOwner) { appState ->
            renderData(appState)
        }
        viewModel.sendRequest(dayAgo)

//        binding.chipToday.setOnClickListener {
//            viewModel.sendRequest(0)
//        }
//
//        binding.chipYesterday.setOnClickListener {
//            viewModel.sendRequest(1)
//        }
//        binding.chipBeforeYesterday.setOnClickListener {
//            viewModel.sendRequest(2)
//        }

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }


        bottomSheetBehavior = BottomSheetBehavior.from(binding.included.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    /*BottomSheetBehavior.STATE_DRAGGING -> TODO("not implemented")
                    BottomSheetBehavior.STATE_COLLAPSED -> TODO("not implemented")
                    BottomSheetBehavior.STATE_EXPANDED -> TODO("not implemented")
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> TODO("not implemented")
                    BottomSheetBehavior.STATE_HIDDEN -> TODO("not implemented")
                    BottomSheetBehavior.STATE_SETTLING -> TODO("not implemented")*/
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                if (DEBUG) {
                    Log.d(TAG, "$slideOffset slideOffset")
                }
            }
        })
    }


    var isMain: Boolean = true
    private fun initMenu() {
        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_bottom_bar, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
//                    R.id.app_bar_fav -> {
//                        Toast.makeText(requireContext(), "app_bar_fav", Toast.LENGTH_SHORT).show()
//                        true
//                    }
//                    R.id.app_bar_settings -> {
//                        //requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container,ChipsFragment.newInstance()).addToBackStack("").commit()
//                        Toast.makeText(requireContext(), "app_bar_settings", Toast.LENGTH_SHORT)
//                            .show()
//                        this@PictureOfTheDayFragment.findNavController()
//                            .navigate(R.id.action_mainFragment_to_settingsFragment)
//                        true
//                    }
//                    android.R.id.home -> {
//                        Toast.makeText(requireContext(), "home", Toast.LENGTH_SHORT).show()
//                        //BottomNavigationDrawerFragment().show(requireActivity().supportFragmentManager,"")
//                        true
//                    }
                    else -> {
                        false
                    }
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)


        binding.fab.setOnClickListener {
            if (isMain) {
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_back_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            } else {
                binding.bottomAppBar.navigationIcon = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_hamburger_menu_bottom_bar
                )
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_plus_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            }
            isMain = !isMain
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {/*TODO */
            }
            AppState.Loading -> {/*TODO */
            }
            is AppState.Success -> {
                binding.imageView.load(appState.pictureOfTheDayResponseData.url) {
                }
                binding.included.bottomSheetDescriptionHeader.text =
                    appState.pictureOfTheDayResponseData.title
                binding.included.bottomSheetDescription.text =
                    appState.pictureOfTheDayResponseData.explanation

            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(dayAgo: Int) = PictureOfTheDayFragment().apply {
            arguments = Bundle().apply {
                putInt(DAY_AGO, dayAgo)
            }
        }
    }

}
