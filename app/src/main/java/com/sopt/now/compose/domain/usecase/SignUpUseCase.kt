package com.sopt.now.compose.domain.usecase

import android.util.Log
import com.sopt.now.compose.component.UiState
import com.sopt.now.compose.domain.AuthRepository
import com.sopt.now.compose.domain.entity.AuthRequestModel
import com.sopt.now.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException


class SignUpUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(request: AuthRequestModel): UiState<User> {
        return withContext(Dispatchers.IO) {
            try {
                val result = authRepository.signUp(request)
                result.fold(
                    onSuccess = { response ->
                        if (response.isSuccessful) {
                            UiState.Success(request.toUserWithUserId(response.headers()[LOCATION].toString()))
                        } else {
                            val errorMessage =
                                JSONObject(response.errorBody()?.string()).getString(MESSAGE)
                            UiState.Error(errorMessage.toString())
                        }
                    },
                    onFailure = { e ->
                        if (e is HttpException) {
                            Log.d("error", "execute: ${e.message}")
                            UiState.Error(e.message())
                        } else {
                            Log.d("error", "execute: ${e.message.toString()}")
                            UiState.Error(e.message.toString())
                        }
                    }
                )
            } catch (e: Exception) {
                Log.d("error", "execute: ${e.message.toString()}")
                UiState.Error(e.message.toString())
            }
        }
    }

    companion object {
        const val MESSAGE = "message"
        const val LOCATION = "location"
    }
}