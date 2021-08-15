package com.zemoga.mobiletest.ui.model

import com.zemoga.mobiletest.persistence.entity.CommentEntity
import com.zemoga.mobiletest.persistence.entity.PostEntity
import com.zemoga.mobiletest.persistence.entity.UserEntity

data class DescriptionModel (
  val postEntity: PostEntity,
  val userEntity: UserEntity,
  val commentListEntity: MutableList<CommentEntity>
)
