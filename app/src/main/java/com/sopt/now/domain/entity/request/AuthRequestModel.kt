package com.sopt.now.domain.entity.request

import com.sopt.now.data.dto.request.RequestLoginDto
import com.sopt.now.data.dto.request.RequestSignUpDto

data class AuthRequestModel(
    val authenticationId: String,
    val password: String,
    val nickname: String,
    val phone: String,
){
    fun toRequestSignup():RequestSignUpDto= RequestSignUpDto(authenticationId,password, nickname, phone)
    fun toRequestLogin():RequestLoginDto = RequestLoginDto(authenticationId, password)
}