package com.zemoga.mobiletest.ui.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zemoga.mobiletest.R
import com.zemoga.mobiletest.databinding.FragmentFavoritesBinding
import com.zemoga.mobiletest.persistence.entity.PostEntity
import com.zemoga.mobiletest.ui.adapter.PostAdapter
import com.zemoga.mobiletest.ui.fragments.base.BaseFragment
import com.zemoga.mobiletest.ui.fragments.tab.TabFragmentDirections
import com.zemoga.mobiletest.ui.listener.IOnBackPressed
import com.zemoga.mobiletest.util.Resource

class FavoritesFragment : BaseFragment() , PostAdapter.AdapterCallback, IOnBackPressed{

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<FavoritesViewModel>()
    private val adapter : PostAdapter = PostAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    override fun onItemClicked(position: Int, post: PostEntity, itemView: View) {
        val action = TabFragmentDirections
            .actionPostsFragmentToDescriptionFragment(post.id)
        findNavController().navigate(action)
    }

    override fun backPressed(): Boolean {
        findNavController().navigateUp()
        return true
    }

    override fun onDestroyView() {
        binding.rvFavorites.adapter = null
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView(){

        binding.rvFavorites.layoutManager = LinearLayoutManager(requireContext() )

        viewModel.getFavoritesPost().observe(viewLifecycleOwner) { resource ->
            when(resource){
                Resource.Loading -> {
                    binding.pbFavorites.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.pbFavorites.visibility = View.INVISIBLE

                    adapter.adapterList(resource.data,
                        requireContext(),
                        this@FavoritesFragment)

                    binding.rvFavorites.adapter = adapter

                    toolbarRefreshButton.visibility = View.VISIBLE
                }
                is Resource.Failure -> {
                    binding.pbFavorites.visibility = View.INVISIBLE
                    Snackbar.make(this@FavoritesFragment.requireView(), getString(R.string.loading_error), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.try_again), null).show()
                }
            }
        }
    }

    fun refreshFavoritesPost() {
        setUpRecyclerView()
    }
}