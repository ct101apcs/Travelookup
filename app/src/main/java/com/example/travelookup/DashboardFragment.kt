package com.example.travelookup

import android.annotation.SuppressLint
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.travelookup.databinding.FragmentDashboardBinding
import com.google.android.material.tabs.TabLayoutMediator

class DashboardFragment : Fragment(){
    private lateinit var binding: FragmentDashboardBinding
    private val tabTitles = mutableMapOf(
        "Home" to R.drawable.ic_home,
        "Ticket" to R.drawable.ic_ticket,
        "Notification" to R.drawable.ic_notification,
        "Profile" to R.drawable.ic_profile
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(layoutInflater)
        setupTabLayoutWithViewPager()
        return binding.root
    }

    @SuppressLint("InflateParams")
    private fun setupTabLayoutWithViewPager() {
        val titles = ArrayList(tabTitles.keys)
        binding.viewPagerDashboard.adapter = DashboardPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPagerDashboard) { tab, position ->
            tab.text = titles[position]
        }.attach()


        tabTitles.values.forEachIndexed { index, drawableId ->
            val view = LayoutInflater.from(requireContext()).inflate(R.layout.tab_title, null) as ImageView
            view.setImageResource(drawableId)

            view.scaleType = ImageView.ScaleType.CENTER_INSIDE

            val layoutParams = ViewGroup.LayoutParams(
                72, 72
            )
            view.layoutParams = layoutParams

            binding.tabLayout.getTabAt(index)?.customView = view
        }
    }
}
