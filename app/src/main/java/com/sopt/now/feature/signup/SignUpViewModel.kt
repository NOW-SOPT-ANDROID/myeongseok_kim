package com.sopt.now.feature.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.domain.usecase.SaveUserInfoUseCase
import com.sopt.now.feature.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val saveUserInfoUseCase: SaveUserInfoUseCase
) : ViewModel() {
    private val _user = MutableLiveData(
        User("", "", "", "")
    )
    val user: LiveData<User> = _user

    fun saveUserInfo() {
        Log.d("testmgk", "saveUserInfo:$user , ${user.value} ")
        Log.d("testmgk", "saveUserInfotoUSerEntity: ${user.value!!.toUserEntity()} ")
        saveUserInfoUseCase.invoke(user.value!!.toUserEntity())
    }

    fun validateUser(user: User): Boolean {
        _user.value = user
        return validateId() && validatePwd() && validateMbti() && validateMbti()
    }

    private fun validateId(): Boolean =
        user.value!!.id.length in MIN_ID_LENGTH..MAX_ID_LENGTH

    private fun validatePwd(): Boolean =
        user.value!!.password.length in MIN_PWD_LENGTH..MAX_PWD_LENGTH

    private fun validateNickname(): Boolean =
        user.value!!.nickname.length >= MIN_NICKNAME_LENGTH

    private fun validateMbti(): Boolean =
        user.value!!.password.length == MBTI_LENGTH

    companion object {
        const val MIN_ID_LENGTH = 6
        const val MAX_ID_LENGTH = 10
        const val MIN_PWD_LENGTH = 8
        const val MAX_PWD_LENGTH = 12
        const val MIN_NICKNAME_LENGTH = 1
        const val MBTI_LENGTH = 4
    }
}