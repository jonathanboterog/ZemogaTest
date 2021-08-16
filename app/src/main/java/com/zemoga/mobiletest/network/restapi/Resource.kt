package com.zemoga.mobiletest.network.restapi

import com.zemoga.mobiletest.persistence.entity.PostEntity
import java.io.Serializable

sealed class Resource<out T> {
    object Loading: Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val exception: Exception) : Resource<Nothing>()
}
