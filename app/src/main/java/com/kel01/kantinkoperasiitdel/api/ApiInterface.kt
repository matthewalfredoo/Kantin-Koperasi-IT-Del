package com.kel01.kantinkoperasiitdel.api

import com.kel01.kantinkoperasiitdel.model.LoginResponseModel
import com.kel01.kantinkoperasiitdel.model.RegisterResponseModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {
    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("no_ktp") noKTP: String,
        @Field("nama_lengkap") namaLengkap: String,
        @Field("no_hp") noHP: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<RegisterResponseModel>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponseModel>
}