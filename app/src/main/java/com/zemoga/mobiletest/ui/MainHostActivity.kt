package com.zemoga.mobiletest.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.zemoga.mobiletest.R
import com.zemoga.mobiletest.databinding.ActivityMainHostBinding
import com.zemoga.mobiletest.persistence.DatabaseApp
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
    }
}