package com.sopt.now.data.datasource

interface SharedPreferenceDataSource {
    fun saveInfo()
    fun getInfo()
    fun clear()
}