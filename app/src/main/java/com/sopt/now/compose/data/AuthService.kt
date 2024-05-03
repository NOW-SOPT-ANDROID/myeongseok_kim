package com.sopt.now.compose.data

import com.sopt.now.compose.data.datasource.request.RequestSignUpDto
import com.sopt.now.compose.data.datasource.response.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto>
}