package com.sopt.now.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.domain.entity.UserEntity
import com.sopt.now.domain.usecase.SignUpUseCase
import com.sopt.now.ui.model.User
import com.sopt.now.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val _signUpState = MutableLiveData<UiState<User>>()
    val signUpState: LiveData<UiState<User>> = _signUpState

    fun signUp(request: UserEntity) {
        _signUpState.value = UiState.Loading
        viewModelScope.launch {
            signUpUseCase(request)
                .onSuccess {
                    _signUpState.value =
                        UiState.Success(
                            User(
                                request.authenticationId,
                                request.password,
                                request.nickname,
                                request.phone,
                            )
                        )
                }
                .onFailure { e ->
                    _signUpState.value = UiState.Error(e.message.toString())
                }
        }
    }
}
