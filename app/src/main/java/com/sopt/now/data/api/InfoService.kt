package com.sopt.now.data.api

import com.sopt.now.data.datasouce.response.ResponseInfoDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface InfoService {
    @GET("/member/info")
    fun getUserInfo(
        @Header("memberid") userId: String,
    ): Call<ResponseInfoDto>
}