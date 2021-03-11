package com.example.daggertest

import org.json.JSONObject
import retrofit2.Response
import java.io.IOException

object ApiResult {
    suspend fun <T: Any> getResult(call: suspend() -> Response<T>): T{
        val response = try {
            call.invoke()
        }catch (e: IOException){
            throw ApiException(e.localizedMessage!!)
        }

        if (response.isSuccessful){
            return response.body()!!
        }else{
            var errorTitle: String? = null
            var errorMessage: String? = null
            val error = response.errorBody()!!.string()
            if (response.code()!= 500){
            error.let {
                val errors = JSONObject(error).getJSONObject("errors")
                if (errors.keys().hasNext())
                    errorTitle = errors.keys().next()
                errorMessage = errors.getJSONArray(errorTitle!!)[0].toString()
                }
                throw ApiException(errorMessage.toString())
            }
            throw ApiException("Something went wrong")
        }
    }
}