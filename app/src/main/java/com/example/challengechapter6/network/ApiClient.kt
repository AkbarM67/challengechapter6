package com.example.challengechapter6.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

const val BASE_URL = "https://6331b372cff0e7bf70f492a8.mockapi.io/"
object ApiClient {
    private val logging: HttpLoggingInterceptor
        get() {
           val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
    val instance : ApiService by lazy{
        val service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
        service.create(ApiService::class.java)
    }
}



