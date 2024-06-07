package com.sopt.now.domain.usecase

import com.sopt.now.data.dto.request.RequestLoginDto
import com.sopt.now.data.model.User
import com.sopt.now.domain.entity.request.AuthRequestModel
import com.sopt.now.domain.repository.AuthRepository
import com.sopt.now.util.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException

class LogInUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(request: AuthRequestModel): UiState<User> {
        return withContext(Dispatchers.IO) {
            try {
                val result = authRepository.logIn(request)
                result.fold(
                    onSuccess = { response ->
                        if (response.isSuccessful) {
                            UiState.Success(
                                User(
                                    request.authenticationId,
                                    request.password,
                                    "",
                                    "",
                                    userId = response.headers()[LOCATION].toString()
                                )
                            )
                        } else {
                            val errorMessage =
                                JSONObject(response.errorBody()?.string()).getString(MESSAGE)
                            UiState.Error(errorMessage.toString())
                        }
                    },
                    onFailure = { e ->
                        if (e is HttpException) {
                            UiState.Error(e.message())
                        } else {
                            UiState.Error(e.message.toString())
                        }
                    }
                )
            } catch (e: Exception) {
                UiState.Error(e.message.toString())
            }
        }
    }

    companion object {
        const val MESSAGE = "message"
        const val LOCATION = "location"
    }
}