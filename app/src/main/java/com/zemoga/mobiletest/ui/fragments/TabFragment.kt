package com.zemoga.mobiletest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.zemoga.mobiletest.R
import com.zemoga.mobiletest.databinding.FragmentTabBinding
import com.zemoga.mobiletest.ui.adapter.ViewPagerAdapter

class TabFragment : Fragment() {

    private var _binding: FragmentTabBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { ViewPagerAdapter(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set title & hide back button
        val title =  requireActivity().findViewById<TextView>(R.id.tvTitle)
        val btBack =  requireActivity().findViewById<ImageView>(R.id.btBack)
        title.text = getString(R.string.post)
        btBack.visibility = GONE

        //Set tabs
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position){
                0 -> tab.text = getString(R.string.all)
                1 -> tab.text = getString(R.string.favorites)
            }
        }.attach()
    }

    override fun onDestroyView() {
        binding.viewPager.adapter = null
        super.onDestroyView()
        _binding = null
    }
}