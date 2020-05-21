package com.jordanmadrigal.mvipractice.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jordanmadrigal.mvipractice.api.RetrofitBuilder
import com.jordanmadrigal.mvipractice.ui.main.state.MainViewState
import com.jordanmadrigal.mvipractice.util.ApiEmptyResponse
import com.jordanmadrigal.mvipractice.util.ApiErrorResponse
import com.jordanmadrigal.mvipractice.util.ApiSuccessResponse
import com.jordanmadrigal.mvipractice.util.DataState

object MainRepository {

    fun getBlogPosts(): LiveData<DataState<MainViewState>>{
        return Transformations
            .switchMap(RetrofitBuilder.apiService.getBlogPosts()){apiResponse ->
                object: LiveData<DataState<MainViewState>>(){
                    override fun onActive() {
                        super.onActive()
                        value = when(apiResponse){

                            is ApiSuccessResponse ->{
                                DataState.data(data = MainViewState(blogPosts = apiResponse.body))
                            }

                            is ApiErrorResponse ->{
                                DataState.error(message = apiResponse.errorMessage)
                            }

                            is ApiEmptyResponse ->{
                                DataState.error(message = "HTTP 204. Returned Nothing")
                            }
                        }
                    }
                }
            }
    }

    fun getUser(userId: String): LiveData<DataState<MainViewState>>{
        return Transformations
            .switchMap(RetrofitBuilder.apiService.getUser(userId)){apiResponse ->
                object: LiveData<DataState<MainViewState>>(){
                    override fun onActive() {
                        super.onActive()
                        value = when(apiResponse){

                            is ApiSuccessResponse ->{
                                DataState.data(data = MainViewState(user = apiResponse.body))
                            }

                            is ApiErrorResponse ->{
                                DataState.error(message = apiResponse.errorMessage)
                            }

                            is ApiEmptyResponse ->{
                                DataState.error(message = "HTTP 204. Returned Nothing")
                            }
                        }
                    }
                }
            }
    }

}