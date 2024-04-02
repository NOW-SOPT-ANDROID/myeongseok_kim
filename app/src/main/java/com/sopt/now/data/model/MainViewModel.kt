package com.sopt.now.data.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel() {
    private val _id = mutableStateOf("")
    val id: State<String> = _id
    private val _password = mutableStateOf("")
    val password: State<String> = _id
    private val _nickname = mutableStateOf("")
    val nickname: State<String> = _id
    private val _mbti = mutableStateOf("")
    val mbti: State<String> = _id
    private val _screenNumber = mutableIntStateOf(0)
    val screenNumber: State<Int> = _screenNumber

    fun setId(text: String) {
        _id.value = text
    }

    fun setPassword(text: String) {
        _password.value = text
    }

    fun setNickname(text: String) {
        _nickname.value = text
    }

    fun setMbti(text: String) {
        _mbti.value = text
    }

    fun setScreen(number: Int) {
        _screenNumber.value += number
    }


}