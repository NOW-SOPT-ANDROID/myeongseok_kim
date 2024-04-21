package com.sopt.now.data.repositoryimpl

import com.sopt.now.data.datasource.SharedPreferenceDataSource
import com.sopt.now.data.dto.UserDto
import com.sopt.now.domain.entity.UserEntity
import com.sopt.now.domain.repository.UserInfoRepository
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val sharedPreferenceDataSource: SharedPreferenceDataSource
) : UserInfoRepository {

    override fun saveUserInfo(user: UserEntity) {
        sharedPreferenceDataSource.saveInfo(
            userDto = UserDto(
                id = user.id,
                password = user.password,
                nickname = user.nickname,
                mbti = user.mbti
            )
        )
    }

    override fun getUserInfo(): UserEntity {
        return sharedPreferenceDataSource.getInfo().toUserEntity()
    }

    override fun clear() {
        sharedPreferenceDataSource.clear()
    }
}
