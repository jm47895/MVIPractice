package com.jordanmadrigal.mvipractice.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jordanmadrigal.mvipractice.ui.main.state.MainStateEvent
import com.jordanmadrigal.mvipractice.ui.main.state.MainStateEvent.*
import com.jordanmadrigal.mvipractice.ui.main.state.MainViewState
import com.jordanmadrigal.mvipractice.util.AbsentLiveData

class MainViewModel : ViewModel(){

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState : LiveData<MainViewState> get() = _viewState

    val dataState: LiveData<MainViewState> = Transformations
        .switchMap(_stateEvent){stateEvent ->
            handleStateEvent(stateEvent)
        }

    private fun handleStateEvent(stateEvent: MainStateEvent): LiveData<MainViewState>{
        return when(stateEvent){

            is GetBlogPostsEvent -> {
                AbsentLiveData.create()
            }

            is GetUserEvent -> {
                AbsentLiveData.create()
            }

            is None -> {
                AbsentLiveData.create()
            }
        }
    }

}