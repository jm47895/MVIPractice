package com.jordanmadrigal.mvipractice.ui

import com.jordanmadrigal.mvipractice.util.DataState

interface DataStateListener {

    fun onDataStateChange(dataState: DataState<*>?)

}