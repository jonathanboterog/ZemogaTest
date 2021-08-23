package com.zemoga.mobiletest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zemoga.mobiletest.R
import com.zemoga.mobiletest.databinding.ActivityMainHostBinding
import com.zemoga.mobiletest.persistence.DatabaseApp
import com.zemoga.mobiletest.ui.fragments.all.AllFragment
import com.zemoga.mobiletest.ui.fragments.favorites.FavoritesFragment
import com.zemoga.mobiletest.ui.listener.IOnBackPressed
import com.zemoga.mobiletest.ui.listener.IOnDeleteAllPost
import com.zemoga.mobiletest.ui.listener.IOnFavoritePressed
import com.zemoga.mobiletest.ui.listener.IOnRefreshPostPressed
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainHostActivity : AppCompatActivity() , AllFragment.IRefreshFragment{

    private lateinit var binding: ActivityMainHostBinding
    @Inject lateinit var databaseApp: DatabaseApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_MobileTest)

        binding = ActivityMainHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.incActionbar.toolbarParentBack
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        binding.fab.setOnClickListener {
            onDeleteAllPost()
        }

        val backButton = binding.incActionbar.btBack
        backButton.setOnClickListener {
            onBackPressed()
        }

        val refreshButton = binding.incActionbar.btRefresh
        refreshButton.setOnClickListener {
            onRefreshPressed()
        }

        val favoriteButton = binding.incActionbar.btFavorite
        favoriteButton.setOnClickListener {
            onFavoritePressed()
        }
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val fragment = navHostFragment?.childFragmentManager?.fragments!![0]

        (fragment as? IOnBackPressed)?.backPressed() ?: run {
            super.onBackPressed()
        }
    }

    private fun onRefreshPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val fragment = navHostFragment?.childFragmentManager?.fragments!![0]

        (fragment as? IOnRefreshPostPressed)?.refreshPostPressed()
    }

    private fun onFavoritePressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val fragment = navHostFragment?.childFragmentManager?.fragments!![0]

        (fragment as? IOnFavoritePressed)?.favoritePressed()
    }

    private fun onDeleteAllPost() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val fragment = navHostFragment?.childFragmentManager?.fragments!![0]

        (fragment as? IOnDeleteAllPost)?.deleteAllPost()
    }

    override fun refreshFavorites() {
        val fragment = supportFragmentManager.findFragmentByTag("f1") as FavoritesFragment?
        fragment?.refreshFavoritesPost()
    }
}