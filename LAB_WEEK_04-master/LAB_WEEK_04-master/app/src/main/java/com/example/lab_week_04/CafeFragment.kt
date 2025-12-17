package com.example.lab_week_04

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab_week_04.databinding.FragmentCafeBinding
import com.google.android.material.tabs.TabLayoutMediator


private val TABS_FIXED = listOf(
    R.string.starbucks_title,
    R.string.janjijiwa_title,
    R.string.kopikenangan_title,
)


private val CAFE_DESCRIPTIONS = listOf(
    "Starbucks Corporation is an American multinational chain of coffeehouses and roastery reserves headquartered in Seattle, Washington.",
    "Janji Jiwa is one of the coffee shop brands that carries a fresh-to-cup concept by serving a selection of classic local Indonesian coffees.",
    "Kopi Kenangan is an Indonesian coffee company. It is one of the fastest-growing coffee chains in Indonesia."
)

class CafeFragment : Fragment() {

    private var _binding: FragmentCafeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCafeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = CafeAdapter(childFragmentManager, lifecycle, CAFE_DESCRIPTIONS)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TABS_FIXED[position])
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}