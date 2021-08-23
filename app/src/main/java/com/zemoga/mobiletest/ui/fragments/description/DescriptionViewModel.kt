package com.zemoga.mobiletest.ui.fragments.description

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.zemoga.mobiletest.repository.comment.CommentRepository
import com.zemoga.mobiletest.repository.post.PostRepository
import com.zemoga.mobiletest.repository.user.UserRepository
import com.zemoga.mobiletest.ui.model.DescriptionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class DescriptionViewModel  @Inject constructor(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val commentRepository: CommentRepository)
    : ViewModel() {

    fun getPostDescription(postId : Int) = liveData(Dispatchers.IO) {
        val postEntity = postRepository.getPostById(postId)
        val userEntity =userRepository.getUserById(postEntity.userId)
        val commentListEntity = commentRepository.getCommentsByPostId(postId)

        emit(DescriptionModel(postEntity, userEntity, commentListEntity))
    }

    fun setFavorite(postId : Int) = liveData(Dispatchers.IO) {
        emit(postRepository.setFavorite(postId))
    }
}