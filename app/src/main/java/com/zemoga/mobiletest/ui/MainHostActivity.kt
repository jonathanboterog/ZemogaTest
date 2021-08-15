package com.zemoga.mobiletest.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.zemoga.mobiletest.R
import com.zemoga.mobiletest.databinding.ActivityMainHostBinding
import com.zemoga.mobiletest.persistence.DatabaseApp
import com.zemoga.mobiletest.ui.listener.IOnBackPressed
import com.zemoga.mobiletest.ui.listener.IOnFavoritePressed
import com.zemoga.mobiletest.ui.listener.IOnRefreshPressed
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainHostActivity : AppCompatActivity() {

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

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val backButton =  findViewById<ImageView>(R.id.btBack)
        backButton.setOnClickListener {
            onBackPressed()
        }

        val refreshButton =  findViewById<ImageView>(R.id.btRefresh)
        refreshButton.setOnClickListener {
            onRefreshPressed()
        }

        val favoriteButton =  findViewById<ImageView>(R.id.btFavorite)
        favoriteButton.setOnClickListener {
            onFavoritePressed()
        }
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val fragment = navHostFragment?.childFragmentManager?.fragments!![0]

        (fragment as? IOnBackPressed)?.onBackPressed() ?: run {
            super.onBackPressed()
        }
    }

    private fun onRefreshPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val fragment = navHostFragment?.childFragmentManager?.fragments!![0]

        (fragment as? IOnRefreshPressed)?.onRefreshPressed()
    }

    private fun onFavoritePressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val fragment = navHostFragment?.childFragmentManager?.fragments!![0]

        (fragment as? IOnFavoritePressed)?.onFavoritePressed()
    }
}