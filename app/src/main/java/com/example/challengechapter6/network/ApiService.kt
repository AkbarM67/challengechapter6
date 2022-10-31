package com.example.challengechapter6.network

import com.example.challengechapter6.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

   @GET("User")
    fun getAllUSer(): Call<RespondeDataUserItem>

//   @GET("buku")
//    fun getAll() : Call<List<ResponDataBukuItem>>
//
//    @POST("buku")
//    fun addData(@Body Buku : ResponDataBukuItem) : Call<ResponDataBukuItem>
//
//    @GET("user")
//    fun getAllUser() : Call<List<RespondeDataUserItem>>
//
     @POST("user")
        fun postUser(@Body user : RespondeDataUserItem) : Call<RespondeDataUserItem>
//
//    @PUT("buku/{id}")
//    fun updateBuku(@Path("id") id: Int, @Body request: DataBuku) : Call<List<putResponseBuku>>
//
//    @DELETE("buku/{id}")
//    fun deleteBuku(@Path("id") id : Int) : Call<Int>

}