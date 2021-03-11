@file:Suppress("DEPRECATION")

package com.example.daggertest

import android.os.Build
import android.text.Html
import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import java.io.IOException

val interceptor: Interceptor = Interceptor { chain ->

    var request: Request = chain.request()
    request = request.newBuilder()
//            .addHeader("User-Agent", "wad497")
//            .addHeader("Apikey","fvgOKNF&Y45141fF")
//            .addHeader("Apiuser","imranrana07@gmail.com")
            .build()

    chain.proceed(request)
}

val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
val apiCall = RetrofitClient.retrofit?.create(ApiCall::class.java)
val handler = CoroutineExceptionHandler { _, exception ->
    Log.v("Network", "Caught $exception")
}

fun errors(jsonRes: String): String?{
    var errorTitle: String? = null
    var errorMessage: String? = null

    jsonRes.let {
        val errors = JSONObject(jsonRes).getJSONObject("errors")
        if (errors.keys().hasNext())
            errorTitle = errors.keys().next()
        errorMessage = errors.getJSONArray(errorTitle!!)[0].toString()
    }
    return errorMessage
}

class ApiException(message: String): IOException(message)
class NoInternetException(message: String): IOException(message)

 fun htmlToString(text: String): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        Html.fromHtml(text).toString()
    }
}