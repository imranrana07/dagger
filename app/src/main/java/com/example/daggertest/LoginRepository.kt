package com.example.daggertest

interface LoginRepository {
    suspend fun getUser(username: String, password: String): BaseResponse<Auth>
}