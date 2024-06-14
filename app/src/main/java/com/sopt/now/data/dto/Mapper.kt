package com.sopt.now.data.dto

import com.sopt.now.data.dto.request.RequestLoginDto
import com.sopt.now.data.dto.request.RequestSignUpDto
import com.sopt.now.data.dto.response.BaseResponse
import com.sopt.now.domain.entity.UserEntity
import com.sopt.now.domain.entity.BaseResponseEntity

internal fun UserEntity.toRequestLoginDto(): RequestLoginDto =
    RequestLoginDto(
        authenticationId = authenticationId,
        password = password
    )

internal fun UserEntity.toRequestSignUpDto(): RequestSignUpDto =
    RequestSignUpDto(
        authenticationId = authenticationId,
        password = password,
        nickname = nickname,
        phone = phone
    )

internal fun BaseResponse<Unit>.toBaseResponseEntity(): BaseResponseEntity =
    BaseResponseEntity(
        code = this.code,
        message = this.message
    )
