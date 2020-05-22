package com.jordanmadrigal.mvipractice.repository

import androidx.lifecycle.LiveData
import com.jordanmadrigal.mvipractice.api.RetrofitBuilder
import com.jordanmadrigal.mvipractice.model.BlogPost
import com.jordanmadrigal.mvipractice.model.User
import com.jordanmadrigal.mvipractice.ui.main.state.MainViewState
import com.jordanmadrigal.mvipractice.util.ApiSuccessResponse
import com.jordanmadrigal.mvipractice.util.DataState
import com.jordanmadrigal.mvipractice.util.GenericApiResponse

object MainRepository {

    fun getBlogPosts(): LiveData<DataState<MainViewState>>{
        return object : NetworkBoundResource<List<BlogPost>, MainViewState>(){

            override fun createCall(): LiveData<GenericApiResponse<List<BlogPost>>> {
                return RetrofitBuilder.apiService.getBlogPosts()
            }

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<BlogPost>>) {
                result.value = DataState.data(data = MainViewState(blogPosts = response.body))
            }

        }.asLiveData()
    }

    fun getUser(userId: String): LiveData<DataState<MainViewState>>{
        return object : NetworkBoundResource<User, MainViewState>(){

            override fun createCall(): LiveData<GenericApiResponse<User>>{
                return RetrofitBuilder.apiService.getUser(userId)
            }

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<User>) {
                result.value = DataState.data(data = MainViewState(user = response.body))
            }


        }.asLiveData()
    }

}