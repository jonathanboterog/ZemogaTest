package com.zemoga.mobiletest.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.zemoga.mobiletest.network.restapi.Resource
import com.zemoga.mobiletest.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getPosts() = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(repository.getPostList())
        } catch (e : Exception) {
            emit(Resource.Failure(e))
        }
    }
}