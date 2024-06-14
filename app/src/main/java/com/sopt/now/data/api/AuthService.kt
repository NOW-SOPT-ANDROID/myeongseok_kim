package com.sopt.now.data.api

import com.sopt.now.data.dto.request.RequestLoginDto
import com.sopt.now.data.dto.request.RequestSignUpDto
import com.sopt.now.data.dto.response.BaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    suspend fun signUp(
        @Body request: RequestSignUpDto,
    ): BaseResponse<Unit>


    @POST("member/login")
    suspend fun login(
        @Body request: RequestLoginDto,
    ): Response<BaseResponse<Unit>>
}