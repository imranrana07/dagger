package com.example.daggertest

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
@Module
object RetrofitClient {

    val gson = GsonBuilder().setLenient().create()
    private val client = OkHttpClient
            .Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
//            .readTimeout(10, TimeUnit.SECONDS)
//            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(logger)
            .build()

    var retrofit: Retrofit? = null
        @Synchronized
        @Singleton
        @Provides
        get() {
            if (field== null){
                field = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
            }
            return field
        }

}