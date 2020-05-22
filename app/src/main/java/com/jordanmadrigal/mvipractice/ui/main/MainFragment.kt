package com.jordanmadrigal.mvipractice.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jordanmadrigal.mvipractice.R
import com.jordanmadrigal.mvipractice.ui.DataStateListener
import com.jordanmadrigal.mvipractice.ui.main.state.MainStateEvent
import java.lang.ClassCastException
import java.lang.Exception

class MainFragment : Fragment() {

    lateinit var viewModel: MainViewModel

    lateinit var dataStateListener: DataStateListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = activity?.run{
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?: throw Exception("Invalid activity")

        subscribeObservers()
    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer {dataState ->

            println("DEBUG: DataState $dataState")

            //handle loading and message
            dataStateListener.onDataStateChange(dataState)
            dataState.data?.let { mainViewState ->
                mainViewState.blogPosts?.let {
                    //Set blog data
                    viewModel.setBlogListData(it)
                }


                mainViewState.user?.let { user ->
                    //Set user data
                    viewModel.setUser(user)
                }
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer {viewState->
            viewState.blogPosts?.let {
                println("DEBUG: Setting blog posts to RecyclerView: $it")
            }

            viewState.user?.let {
                println("DEBUG: Setting user date $it")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_get_user -> triggerGetUserEvent()

            R.id.action_get_blog -> triggerGetBlogsEvent()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun triggerGetBlogsEvent() {

        viewModel.setStateEvent(MainStateEvent.GetBlogPostsEvent())
    }

    private fun triggerGetUserEvent() {
        viewModel.setStateEvent(MainStateEvent.GetUserEvent("1"))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            dataStateListener = context as DataStateListener
        }catch (e: ClassCastException){
            println("DEBUG: $context must implement DataStateListener")
        }
    }
}