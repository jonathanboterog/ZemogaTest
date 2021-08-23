package com.zemoga.mobiletest.ui.fragments.all

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.zemoga.mobiletest.persistence.entity.PostEntity
import com.zemoga.mobiletest.repository.post.PostRepository
import com.zemoga.mobiletest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AllViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {

    fun getPosts() = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(postRepository.getPosts())
        } catch (e : Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun deletePost(postEntity: PostEntity) = liveData(Dispatchers.IO) {
        emit(postRepository.deletePost(postEntity))
    }

    fun setRead(postId : Int) = liveData(Dispatchers.IO) {
        emit(postRepository.setRead(postId))
    }

}