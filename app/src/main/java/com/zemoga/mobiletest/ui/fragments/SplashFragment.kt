package com.zemoga.mobiletest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.zemoga.mobiletest.R
import com.zemoga.mobiletest.databinding.FragmentSplashBinding
import com.zemoga.mobiletest.ui.viewmodel.AppViewModel

class SplashFragment : BaseFragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<AppViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set title & hide back button
        toolbarTitle.text = getString(R.string.post)
        toolbarBackButton.visibility = View.GONE
        toolbarRefreshButton.visibility = View.INVISIBLE
        toolbarFavoriteButton.visibility = View.INVISIBLE

        viewModel.requestServiceApi().observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_splashFragment_to_tabFragment)
        }
    }
}