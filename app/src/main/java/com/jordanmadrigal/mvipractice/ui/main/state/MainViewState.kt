package com.jordanmadrigal.mvipractice.ui.main.state

import com.jordanmadrigal.mvipractice.model.BlogPost
import com.jordanmadrigal.mvipractice.model.User

data class MainViewState(

    var blogPosts : List<BlogPost>? = null,
    var user : User? = null
)