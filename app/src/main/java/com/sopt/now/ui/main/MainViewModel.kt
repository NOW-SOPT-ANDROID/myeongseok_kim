package com.sopt.now.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.User

class MainViewModel : ViewModel() {
    private val _userData = MutableLiveData<List<User>>()
    val userData = _userData

    private val _myData = MutableLiveData<User>()
    val myData = _myData

    init {
        _userData.value =
            listOf(
                User("", "", "배찬우", "infp"),
                User("", "", "김민우", "intp"),
                User("", "", "", ""),
                User("", "", "", ""),
                User("", "", "", ""),
                User("", "", "", ""),
                User("", "", "", ""),
                User("", "", "", ""),
                User("", "", "", ""),
                User("", "", "", ""),
            )
    }

    fun setMydata(user: User) {
        myData.value = user
    }

}