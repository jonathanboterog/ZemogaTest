package com.zemoga.mobiletest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zemoga.mobiletest.R
import com.zemoga.mobiletest.databinding.FragmentAllBinding
import com.zemoga.mobiletest.network.restapi.Resource
import com.zemoga.mobiletest.persistence.DatabaseApp
import com.zemoga.mobiletest.persistence.entity.PostEntity
import com.zemoga.mobiletest.ui.adapter.PostAdapter
import com.zemoga.mobiletest.ui.viewmodel.AppViewModel
import javax.inject.Inject

class AllFragment : Fragment(), PostAdapter.AdapterCallback{

    private var _binding: FragmentAllBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var databaseApp: DatabaseApp
    private val adapter : PostAdapter = PostAdapter()
    private val viewModel by activityViewModels<AppViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPosts.layoutManager = LinearLayoutManager(requireContext() )

        viewModel.getPosts().observe(viewLifecycleOwner) { resource ->
            when(resource){
                Resource.Loading -> {
                    binding.progressBar.visibility = VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = INVISIBLE

                    adapter.adapterList(resource.data,
                        requireContext(),
                        this@AllFragment)

                    binding.rvPosts.adapter = adapter

                    val btRefresh =  requireActivity().findViewById<ImageView>(R.id.btRefresh)
                    btRefresh.visibility = VISIBLE
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = INVISIBLE
                    Snackbar.make(view, getString(R.string.loading_error), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.refresh), null).show()
                }
            }
        }
    }

    override fun onItemClicked(position: Int, post: PostEntity, itemView: View) {
        val action = TabFragmentDirections
            .actionPostsFragmentToDescriptionFragment(post.id)

        viewModel.setRead(post.id).observe(viewLifecycleOwner) {
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}