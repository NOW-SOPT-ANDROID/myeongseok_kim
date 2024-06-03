package com.sopt.now.data.api

import com.sopt.now.data.datasouce.request.RequestLoginDto
import com.sopt.now.data.datasouce.request.RequestSignUpDto
import com.sopt.now.data.datasouce.response.BaseResponse
import com.sopt.now.data.datasouce.response.ResponseLoginDto
import com.sopt.now.data.datasouce.response.ResponseSignUpDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    suspend fun signUp(
        @Body request: RequestSignUpDto,
    ): Response<BaseResponse<Unit>>


    @POST("member/login")
    suspend fun login(
        @Body request: RequestLoginDto,
    ): Response<BaseResponse<Unit>>
}