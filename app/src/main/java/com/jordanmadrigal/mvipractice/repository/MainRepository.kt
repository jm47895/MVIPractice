package com.jordanmadrigal.mvipractice.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jordanmadrigal.mvipractice.api.RetrofitBuilder
import com.jordanmadrigal.mvipractice.ui.main.state.MainViewState
import com.jordanmadrigal.mvipractice.util.ApiEmptyResponse
import com.jordanmadrigal.mvipractice.util.ApiErrorResponse
import com.jordanmadrigal.mvipractice.util.ApiSuccessResponse

object MainRepository {

    fun getBlogPosts(): LiveData<MainViewState>{
        return Transformations
            .switchMap(RetrofitBuilder.apiService.getBlogPosts()){apiResponse ->
                object: LiveData<MainViewState>(){
                    override fun onActive() {
                        super.onActive()
                        value = when(apiResponse){

                            is ApiSuccessResponse ->{
                                MainViewState(blogPosts = apiResponse.body)
                            }

                            is ApiErrorResponse ->{
                                MainViewState() //TODO Handle Errors
                            }

                            is ApiEmptyResponse ->{
                                MainViewState() //TODO Handle Empty responses
                            }
                        }
                    }
                }
            }
    }

    fun getUser(userId: String): LiveData<MainViewState>{
        return Transformations
            .switchMap(RetrofitBuilder.apiService.getUser(userId)){apiResponse ->
                object: LiveData<MainViewState>(){
                    override fun onActive() {
                        super.onActive()
                        value = when(apiResponse){

                            is ApiSuccessResponse ->{
                                MainViewState(user = apiResponse.body)
                            }

                            is ApiErrorResponse ->{
                                MainViewState() //TODO Handle Errors
                            }

                            is ApiEmptyResponse ->{
                                MainViewState() //TODO Handle Empty responses
                            }
                        }
                    }
                }
            }
    }

}