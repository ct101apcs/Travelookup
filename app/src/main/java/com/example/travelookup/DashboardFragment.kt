package com.example.travelookup

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.travelookup.databinding.FragmentDashboardBinding
import com.google.android.material.tabs.TabLayoutMediator

class DashboardFragment : Fragment(){
    private lateinit var binding: FragmentDashboardBinding
    private val tabTitles = arrayListOf("Home", "Ticket", "Notification", "Profile")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        setupTabLayoutWithViewPager()

        return binding.root
    }

    @SuppressLint("InflateParams")
    private fun setupTabLayoutWithViewPager() {
        binding.viewPagerDashboard.adapter = DashboardPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPagerDashboard) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        for (i in 0 until tabTitles.size) {
            val view = LayoutInflater.from(requireContext()).inflate(R.layout.tab_title,null) as View
            binding.tabLayout.getTabAt(i)?.customView = view
        }
    }
}