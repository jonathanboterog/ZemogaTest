package com.zemoga.mobiletest.network.restapi

import com.zemoga.mobiletest.persistence.entity.PostEntity
import java.io.Serializable

sealed class Resource : Serializable{
    object Loading : Resource()
    data class Success(val data : MutableList<PostEntity>) : Resource()
    data class Failure(val exception : Exception?) : Resource()
}
