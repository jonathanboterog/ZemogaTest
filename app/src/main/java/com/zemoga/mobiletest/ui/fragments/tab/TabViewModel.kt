package com.zemoga.mobiletest.ui.fragments.tab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.zemoga.mobiletest.repository.commentdao.CommentRepository
import com.zemoga.mobiletest.repository.postdao.PostRepository
import com.zemoga.mobiletest.repository.userdao.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class TabViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val commentRepository: CommentRepository)
    : ViewModel() {

    fun deleteAll() = liveData(Dispatchers.IO) {
        emit(postRepository.deleteAll())
        emit(userRepository.deleteAll())
        emit(commentRepository.deleteAll())
    }
}