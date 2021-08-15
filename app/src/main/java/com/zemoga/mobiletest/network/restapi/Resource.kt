package com.zemoga.mobiletest.network.restapi

import com.zemoga.mobiletest.network.restapi.model.Post
import java.io.Serializable

sealed class Resource : Serializable{
    object Loading : Resource()
    data class Success(val data : ArrayList<Post>) : Resource()
    data class Failure(val exception : Exception) : Resource()
}
