package com.sopt.now.compose.data

import com.sopt.now.compose.data.datasource.request.RequestLoginDto
import com.sopt.now.compose.data.datasource.request.RequestSignUpDto
import com.sopt.now.compose.data.datasource.response.ResponseLoginDto
import com.sopt.now.compose.data.datasource.response.ResponseSignUpDto
import com.sopt.now.compose.data.datasource.response.ResponseUserInfoDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto>

    @POST("member/login")
    fun login(
        @Body request: RequestLoginDto,
    ): Call<ResponseLoginDto>

    @GET("/member/info")
    suspend fun getUserInfo(
        @Header("memberid") userId: String?,
    ): Response<ResponseUserInfoDto>
}