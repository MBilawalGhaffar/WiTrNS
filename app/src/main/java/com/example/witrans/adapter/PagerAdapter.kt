package com.example.witrans.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.witrans.navigation.AllTourFragment
import com.example.witrans.navigation.CurrentTourFragment
import com.example.witrans.navigation.DriverFragment

class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return CurrentTourFragment()
            1 -> return AllTourFragment()
        }
        return DriverFragment()
    }

}