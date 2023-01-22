package com.girogevoro.material_design.screens.earth

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.girogevoro.material_design.screens.earth.PictureOfTheDay.PictureOfTheDayFragment

class ViewPager2AdapterForEarthFragment(fr: Fragment) : FragmentStateAdapter(fr) {
    private val fragments = arrayOf(PictureOfTheDayFragment.newInstance(0), PictureOfTheDayFragment.newInstance(1), PictureOfTheDayFragment.newInstance(2))

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }


}