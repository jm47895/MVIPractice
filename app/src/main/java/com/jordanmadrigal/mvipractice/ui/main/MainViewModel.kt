package com.jordanmadrigal.mvipractice.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jordanmadrigal.mvipractice.ui.main.state.MainViewState

class MainViewModel : ViewModel(){

    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState : LiveData<MainViewState> get() = _viewState
}