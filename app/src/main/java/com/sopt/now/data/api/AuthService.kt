package com.sopt.now.data.api

import com.sopt.now.data.datasouce.request.RequestLoginDto
import com.sopt.now.data.datasouce.request.RequestSignUpDto
import com.sopt.now.data.datasouce.response.BaseResponse
import com.sopt.now.data.datasouce.response.ResponseLoginDto
import com.sopt.now.data.datasouce.response.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<BaseResponse<Unit>>


    @POST("member/login")
    fun login(
        @Body request: RequestLoginDto,
    ): Call<BaseResponse<Unit>>
}