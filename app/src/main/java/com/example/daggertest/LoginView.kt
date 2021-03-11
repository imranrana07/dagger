package com.example.daggertest

interface LoginView {
    suspend fun successful(loginResponse: String)
    suspend fun loginFailed(message: String)
}