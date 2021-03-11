package com.example.daggertest


import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiCall {

    //user
    @FormUrlEncoded
    @POST("user/login")
    suspend fun userLogin(@Field("email") username: String, @Field("password") password: String): Response<BaseResponse<Auth>>

}