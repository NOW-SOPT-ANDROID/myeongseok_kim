package com.sopt.now.data.datasourceimpl

import android.content.SharedPreferences
import androidx.core.content.edit
import com.sopt.now.data.datasource.SharedPreferenceDataSource
import com.sopt.now.data.dto.UserDto
import javax.inject.Inject
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SharedPreferenceDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SharedPreferenceDataSource {
    override fun saveInfo(userDto: UserDto) {
        val json = Json.encodeToString(userDto)
        sharedPreferences.edit { putString(USER_INFO, json) }
    }

    override fun getInfo() :UserDto {
        val json = sharedPreferences.getString(USER_INFO, "")
        if (json.isNullOrBlank()) return UserDto("", "", "", "")
        return Json.decodeFromString(json)
    }

    override fun clear() {
        sharedPreferences.edit {
            remove(USER_INFO)
        }
    }

    companion object {
        const val USER_INFO = "userId"
    }
}