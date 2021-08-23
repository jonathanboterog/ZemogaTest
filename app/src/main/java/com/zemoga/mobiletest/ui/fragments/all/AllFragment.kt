package com.zemoga.mobiletest.ui.fragments.all

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.zemoga.mobiletest.R
import com.zemoga.mobiletest.databinding.FragmentAllBinding
import com.zemoga.mobiletest.persistence.entity.PostEntity
import com.zemoga.mobiletest.ui.adapter.PostAdapter
import com.zemoga.mobiletest.ui.dialog.GeneralDialog
import com.zemoga.mobiletest.ui.fragments.base.BaseFragment
import com.zemoga.mobiletest.ui.fragments.tab.TabFragmentDirections
import com.zemoga.mobiletest.util.Resource

class AllFragment : BaseFragment(), PostAdapter.AdapterCallback{

    private var _binding: FragmentAllBinding? = null
    private val binding get() = _binding!!
    private val adapter : PostAdapter = PostAdapter()
    private val viewModel by activityViewModels<AllViewModel>()
    private var postList :MutableList<PostEntity> = mutableListOf()
    var event : IRefreshFragment? = null

    interface IRefreshFragment{
        fun refreshFavorites()
    }

    companion object{
        private val TAG = AllFragment::class.java.simpleName
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            event = activity as IRefreshFragment?
        } catch (e: ClassCastException) {
            throw ClassCastException("Error in retrieving data. Please try again")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    override fun onItemClicked(position: Int, post: PostEntity, itemView: View) {
        val action = TabFragmentDirections
            .actionPostsFragmentToDescriptionFragment(post.id)

        viewModel.setRead(post.id).observe(viewLifecycleOwner) {
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        binding.rvPosts.adapter = null
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView(){
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

                    postList = resource.data
                    binding.rvPosts.adapter = adapter
                    toolbarRefreshButton.visibility = VISIBLE
                    ItemTouchHelper(simpleCallback).attachToRecyclerView(binding.rvPosts)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = INVISIBLE
                    Snackbar.make(this@AllFragment.requireView(), getString(R.string.loading_error), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.try_again), null).show()
                }
            }
        }
    }

    private var simpleCallback = object : ItemTouchHelper.SimpleCallback(0,
        ItemTouchHelper.RIGHT){

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            Log.d(TAG, "onMove event")
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition

            Log.d(TAG, "onSwiped event direction = $direction")

            when(direction){
                ItemTouchHelper.RIGHT ->{

                    viewModel.deletePost(postList[position]).observe(viewLifecycleOwner) {
                        postList.removeAt(position)
                        adapter.notifyItemRemoved(position)

                        event?.refreshFavorites()

                        val dialog = GeneralDialog()
                        dialog.show(this@AllFragment.requireContext(),
                            getString(R.string.post_removed),
                            "deleted.json", 2000)
                    }
                }
            }
        }
    }
}