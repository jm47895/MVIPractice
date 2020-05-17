package com.jordanmadrigal.mvipractice.api

import androidx.lifecycle.LiveData
import com.jordanmadrigal.mvipractice.model.BlogPost
import com.jordanmadrigal.mvipractice.model.User
import com.jordanmadrigal.mvipractice.util.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService{

    @GET("placeholder/user/{userId}")
    fun getUser(@Path("userId") userId: String): LiveData<GenericApiResponse<User>>

    @GET("placeholder/blogs")
    fun getBlogPosts(): LiveData<GenericApiResponse<List<BlogPost>>>
}