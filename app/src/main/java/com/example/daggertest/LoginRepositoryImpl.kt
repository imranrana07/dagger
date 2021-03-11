package com.example.daggertest


class LoginRepositoryImpl: LoginRepository {

    override suspend fun getUser(username: String, password: String): BaseResponse<Auth> {
        return ApiResult.getResult {apiCall?.userLogin(username,password)!!}
    }

}