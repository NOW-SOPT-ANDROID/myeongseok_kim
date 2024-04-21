package com.sopt.now.data.datasource

import com.sopt.now.data.dto.UserDto

interface SharedPreferenceDataSource {
    fun saveInfo(userDto: UserDto)
    fun getInfo(): UserDto
    fun clear()
}