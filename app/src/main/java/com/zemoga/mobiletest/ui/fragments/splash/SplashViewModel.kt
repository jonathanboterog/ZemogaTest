package com.zemoga.mobiletest.ui.fragments.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.zemoga.mobiletest.repository.retrofit.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val retrofitRepository: RetrofitRepository)
    : ViewModel() {

    fun requestServiceApi() = liveData(Dispatchers.IO) {
        emit(retrofitRepository.getAll())
    }
}