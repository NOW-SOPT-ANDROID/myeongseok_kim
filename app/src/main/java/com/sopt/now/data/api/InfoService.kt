package com.sopt.now.data.api

import com.sopt.now.data.datasouce.response.ResponseInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface InfoService {
    @GET("/member/info")
    suspend fun getUserInfo(
        @Header("memberid") userId: String,
    ): Response<ResponseInfoDto>
}