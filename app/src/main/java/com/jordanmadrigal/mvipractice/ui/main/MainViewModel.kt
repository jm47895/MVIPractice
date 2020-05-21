package com.jordanmadrigal.mvipractice.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jordanmadrigal.mvipractice.model.BlogPost
import com.jordanmadrigal.mvipractice.model.User
import com.jordanmadrigal.mvipractice.repository.MainRepository
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
                return MainRepository.getBlogPosts()
            }

            is GetUserEvent -> {
                return MainRepository.getUser(stateEvent.userId)
            }

            is None -> {
                AbsentLiveData.create()
            }
        }
    }

    fun setBlogListData(blogPosts: List<BlogPost>){
        val update = getCurrentOrNewViewState()
        update.blogPosts = blogPosts
        _viewState.value = update
    }

    fun setUser(user: User){
        val update = getCurrentOrNewViewState()
        update.user = user
        _viewState.value = update
    }

    fun getCurrentOrNewViewState(): MainViewState{
        return viewState.value ?: MainViewState()
    }

    fun setStateEvent(event : MainStateEvent){
        _stateEvent.value = event
    }
}