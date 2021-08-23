package com.zemoga.mobiletest.ui.fragments.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.zemoga.mobiletest.repository.post.PostRepository
import com.zemoga.mobiletest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val postRepository: PostRepository)
    : ViewModel() {

    fun getFavoritesPost() = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(postRepository.getFavoritesPost())
        } catch (e : Exception) {
            emit(Resource.Failure(e))
        }
    }
}