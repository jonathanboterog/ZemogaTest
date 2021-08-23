package com.zemoga.mobiletest.ui.fragments.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zemoga.mobiletest.R
import com.zemoga.mobiletest.databinding.FragmentDescriptionBinding
import com.zemoga.mobiletest.ui.adapter.CommentAdapter
import com.zemoga.mobiletest.ui.fragments.base.BaseFragment
import com.zemoga.mobiletest.ui.listener.IOnBackPressed
import com.zemoga.mobiletest.ui.listener.IOnFavoritePressed

class DescriptionFragment : BaseFragment(), IOnBackPressed, IOnFavoritePressed {

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!
    private var postId : Int = 0
    private val viewModel by activityViewModels<DescriptionViewModel>()
    private val adapter : CommentAdapter = CommentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postId = DescriptionFragmentArgs.fromBundle(requireArguments()).postId
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvComments.layoutManager = LinearLayoutManager(requireContext() )

        //Hide title & show back button
        toolbarTitle.text = ""
        toolbarBackButton.visibility = View.VISIBLE
        toolbarRefreshButton.visibility = View.GONE

        viewModel.getPostDescription(postId).observe(viewLifecycleOwner) { description ->

            //Post description
            binding.tvTitle.text = description.postEntity.title
            binding.tvBody.text = description.postEntity.body

            //User information
            binding.tvName.text = description.userEntity.name
            binding.tvEmail.text = description.userEntity.email
            binding.tvPhone.text = description.userEntity.phone
            binding.tvWebsite.text = description.userEntity.website

            if(description.postEntity.favorite)
                toolbarFavoriteButton.setImageResource(R.drawable.ic_favorite)
            else
                toolbarFavoriteButton.setImageResource(R.drawable.ic_set_favorite)
            toolbarFavoriteButton.visibility = View.VISIBLE

            adapter.adapterList(description.commentListEntity,
                requireContext(),
                null)

            binding.rvComments.adapter = adapter
        }
    }

    override fun backPressed(): Boolean {
        findNavController().popBackStack(R.id.tabFragment, false)
        return true
    }

    override fun favoritePressed() {
        viewModel.setFavorite(postId).observe(viewLifecycleOwner) { state ->
            if(state){
                toolbarFavoriteButton.setImageResource(R.drawable.ic_favorite)
            } else {
                toolbarFavoriteButton.setImageResource(R.drawable.ic_set_favorite)
            }
        }
    }

    override fun onDestroyView() {
        binding.rvComments.adapter = null
        super.onDestroyView()
        _binding = null
    }
}