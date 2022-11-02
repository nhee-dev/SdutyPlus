package com.d205.data.repository.user.remote

import com.d205.data.model.user.UserEntity
import com.d205.domain.model.user.UserDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response

interface UserRemoteDataSource {
    suspend fun addKakaoUser(user: UserDto): ApiResponse<String>
    suspend fun addNaverUser(user: UserDto): ApiResponse<String>
    suspend fun checkNickname(nickname: String): ApiResponse<String>
    suspend fun loginKakaoUser(token: String): ApiResponse<UserEntity>
    suspend fun loginNaverUser(token: String): ApiResponse<UserEntity>
}