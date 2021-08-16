package com.zemoga.mobiletest.ui.fragments

import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.zemoga.mobiletest.R

open class  BaseFragment : Fragment() {

    val toolbarBackButton : ImageView by lazy {
        requireActivity().findViewById(R.id.btBack)
    }

    val toolbarFavoriteButton : ImageView by lazy {
        requireActivity().findViewById(R.id.btFavorite)
    }

    val toolbarRefreshButton : ImageView by lazy {
        requireActivity().findViewById(R.id.btRefresh)
    }

    val toolbarTitle : TextView by lazy {
        requireActivity().findViewById(R.id.tvTitle)
    }

}