package com.example.daggertest

import java.io.Serializable


data class BaseResponse <T> (
        val code: String,
        val errors: Any,
        val fade: Boolean,
        val payload: T,
        val success: Boolean,
        val type: String
):Serializable