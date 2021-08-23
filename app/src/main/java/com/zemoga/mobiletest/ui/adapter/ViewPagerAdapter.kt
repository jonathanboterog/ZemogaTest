package com.zemoga.mobiletest.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zemoga.mobiletest.ui.fragments.all.AllFragment
import com.zemoga.mobiletest.ui.fragments.favorites.FavoritesFragment

class ViewPagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> AllFragment()
            1 -> FavoritesFragment()
            else -> AllFragment()
        }
    }
}