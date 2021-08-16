package com.zemoga.mobiletest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.zemoga.mobiletest.network.restapi.Resource
import com.zemoga.mobiletest.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun requestServiceApi() = liveData(Dispatchers.IO) {
        emit(repository.requestServiceApi())
    }

    fun getPosts() = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(repository.getPostList())
        } catch (e : Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun getPostDescription(postId : Int) = liveData(Dispatchers.IO) {
        emit(repository.getPostDescription(postId))
    }

    fun deleteDatabaseRegister() = liveData(Dispatchers.IO) {
        emit(repository.deleteDatabaseRegister())
    }

    fun setRead(postId : Int) = liveData(Dispatchers.IO) {
        emit(repository.setRead(postId))
    }

    fun setFavorite(postId : Int) = liveData(Dispatchers.IO) {
        emit(repository.setFavorite(postId))
    }

    fun getFavoritesPost() = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(repository.getFavoritesPost())
        } catch (e : Exception) {
            emit(Resource.Failure(e))
        }
    }
}