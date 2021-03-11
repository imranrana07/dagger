package com.example.daggertest

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Auth (
        @SerializedName("access_token")
        val accessToken: String,
        val data: User,
        val message:String,
        val token_validity:String,
        val verified:Boolean,
        val is_phone:Boolean
):Serializable{
    data class User(
            val email: String,
            val id: Int,
            val name: String,
            val profile_path: String?
    )
}