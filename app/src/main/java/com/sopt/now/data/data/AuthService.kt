package com.sopt.now.data.data

import com.sopt.now.data.datasouce.RequestSignUpDto
import com.sopt.now.data.datasouce.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto>
}