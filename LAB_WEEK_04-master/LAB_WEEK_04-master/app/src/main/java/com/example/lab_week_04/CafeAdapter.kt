package com.example.lab_week_04

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class CafeAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val cafeDescriptions: List<String>
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {

        return cafeDescriptions.size
    }

    override fun createFragment(position: Int): Fragment {

        val description = cafeDescriptions[position]

        return CafeDetailFragment.newInstance(description)
    }
}