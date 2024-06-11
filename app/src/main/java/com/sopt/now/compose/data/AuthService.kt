package com.sopt.now.compose.data

import com.sopt.now.compose.data.datasource.request.RequestLoginDto
import com.sopt.now.compose.data.datasource.request.RequestSignUpDto
import com.sopt.now.compose.data.datasource.response.ResponseLoginDto
import com.sopt.now.compose.data.datasource.response.ResponseSignUpDto
import com.sopt.now.compose.data.datasource.response.ResponseUserInfoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    suspend fun signUp(
        @Body request: RequestSignUpDto,
    ): Response<ResponseSignUpDto>

    @POST("member/login")
    suspend fun login(
        @Body request: RequestLoginDto,
    ): Response<ResponseLoginDto>

    @GET("/member/info")
    suspend fun getUserInfo(
        @Header("memberid") userId: String?,
    ): Response<ResponseUserInfoDto>
}