package com.sopt.now.data.data

import com.sopt.now.data.datasouce.RequestLoginDto
import com.sopt.now.data.datasouce.ResponseLoginDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("member/login")
    fun login(
        @Body request: RequestLoginDto,
    ): Call<ResponseLoginDto>
}