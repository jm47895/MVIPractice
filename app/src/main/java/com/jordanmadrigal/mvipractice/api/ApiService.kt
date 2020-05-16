package com.jordanmadrigal.mvipractice.api

import com.jordanmadrigal.mvipractice.model.BlogPost
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService{

    @GET("placeholder/user/{userId}")
    fun getUser(@Path("userId") userId: String)

    @GET("placeholder/blogs")
    fun getBlogPosts(): List<BlogPost>
}