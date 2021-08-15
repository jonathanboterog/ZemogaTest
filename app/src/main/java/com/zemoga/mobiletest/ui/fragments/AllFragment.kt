package com.zemoga.mobiletest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zemoga.mobiletest.databinding.FragmentAllBinding
import com.zemoga.mobiletest.ui.adapter.PostAdapter
import com.zemoga.mobiletest.ui.data.PostModel

class AllFragment : Fragment(), PostAdapter.AdapterCallback{

    private var _binding: FragmentAllBinding? = null
    private val binding get() = _binding!!

    private val adapter : PostAdapter = PostAdapter()

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
        setUpObserver()
    }

    private fun setUpRecyclerView() {
        binding.rvPosts.layoutManager = LinearLayoutManager(requireContext() )
    }

    private fun setUpObserver() {

        val list : MutableList<PostModel> = mutableListOf()

        list.add(PostModel(
            id = 1,
            text = "Texto de prueba",
            indicator = true,
            favorite = true)
        )

        list.add(PostModel(
            id = 2,
            text = "Texto de prueba con texto mas largo. Texto de prueba con texto mas largo. Texto de prueba con texto mas largo. Texto de prueba con texto mas largo." +
                    "Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.Texto de prueba con texto mas largo." +
                    "Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.Texto de prueba con texto mas largo." +
                    "Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.",
            indicator = true,
            favorite = true)
        )

        list.add(PostModel(
            id = 3,
            text = "Texto de prueba con texto mas largo. Texto de prueba con texto mas largo. Texto de prueba con texto mas largo. Texto de prueba con texto mas largo." +
                    "Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.Texto de prueba con texto mas largo." +
                    "Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.Texto de prueba con texto mas largo." +
                    "Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.",
            indicator = true,
            favorite = false)
        )


        list.add(PostModel(
            id = 4,
            text = "Texto de prueba",
            indicator = true,
            favorite = true)
        )

        list.add(PostModel(
            id = 5,
            text = "Texto de prueba con texto mas largo. Texto de prueba con texto mas largo. Texto de prueba con texto mas largo. Texto de prueba con texto mas largo." +
                    "Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.Texto de prueba con texto mas largo." +
                    "Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.Texto de prueba con texto mas largo." +
                    "Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.Texto de prueba con texto mas largo.",
            indicator = true,
            favorite = false)
        )


        list.add(PostModel(
            id = 6,
            text = "Texto de prueba",
            indicator = true,
            favorite = true)
        )


        list.add(PostModel(
            id = 1,
            text = "Texto de prueba",
            indicator = true,
            favorite = true)
        )

        adapter.adapterListOptionMenu(list, requireContext(), this@AllFragment)
        binding.rvPosts.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(position: Int, menuModel: PostModel, itemView: View) {
        Snackbar.make(itemView, "Selected id = ${menuModel.id}", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }
}