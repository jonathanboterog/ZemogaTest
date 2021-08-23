package com.zemoga.mobiletest.ui.fragments.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.zemoga.mobiletest.R
import com.zemoga.mobiletest.databinding.FragmentTabBinding
import com.zemoga.mobiletest.ui.adapter.ViewPagerAdapter
import com.zemoga.mobiletest.ui.dialog.GeneralDialog
import com.zemoga.mobiletest.ui.fragments.base.BaseFragment
import com.zemoga.mobiletest.ui.listener.IOnDeleteAllPost
import com.zemoga.mobiletest.ui.listener.IOnRefreshPostPressed
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabFragment : BaseFragment() , IOnRefreshPostPressed , IOnDeleteAllPost{

    private var _binding: FragmentTabBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { ViewPagerAdapter(requireActivity()) }
    private val viewModel by activityViewModels<TabViewModel>()

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
        toolbarTitle.text = getString(R.string.post)
        toolbarBackButton.visibility = GONE
        toolbarRefreshButton.visibility = VISIBLE
        toolbarFavoriteButton.visibility = INVISIBLE
        fab.visibility = VISIBLE

        //Set tabs
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager
        viewPager.adapter = adapter
        viewPager.isSaveEnabled = false

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position){
                0 -> tab.text = getString(R.string.all)
                1 -> tab.text = getString(R.string.favorites)
            }
        }.attach()
    }

    override fun refreshPostPressed() {
        viewModel.deleteAll().observe(viewLifecycleOwner) {
            findNavController().popBackStack()
            findNavController().navigate(R.id.splashFragment)
        }
    }

    override fun deleteAllPost() {
        viewModel.deleteAll().observe(viewLifecycleOwner) {
            findNavController().popBackStack()
            findNavController().navigate(R.id.tabFragment)

            val generalDialog = GeneralDialog()
            generalDialog.show(requireContext(),
                getString(R.string.posts_removed),
                "deleted.json", 2000)
        }
    }

    override fun onDestroyView() {
        binding.viewPager.adapter = null
        super.onDestroyView()
        _binding = null
    }
}