package com.informatika19100019.nifaawali.kalkulator.network

import android.telecom.Call
import com.informatika19100019.nifaawali.kalkulator.model.kalkulator
import com.informatika19100019.nifaawali.kalkulator.model.responadmin


interface ApiService {
    @GET("read.php")
    fun getBarang(): Call<kalkulator>

    annotation class GET(val value: String)

    @FormUrlEncoded
    @POST("create.php")
    fun insertBarang(
        @Field("angka_1") angka_1: String?,
        @Field("angka_2") angka_2: String?,
        @Field("simbol") simbol: String?,
        @Field("hasil") hasil: String?
    ): Call<responkalkulator>

    @FormUrlEncoded
    @POST("update.php")
    fun updateBarang(
        @Field("id") id: String?,
        @Field("angka_1") angka_1: String?,
        @Field("angka_2") angka_2: String?,
        @Field("simbol") simbol: String?,
        @Field("hasil") hasil: String?
    ): Call<responkalkulator>

    @FormUrlEncoded
    @POST("delete.php")
    fun deleteBarang(
        @Field("id") id: String?
    ): Call<responkalkulator>

    annotation class POST(val value: String)

    @FormUrlEncoded
    @POST("login.php")
    fun logIn(
        @Field("username") Username : String?,
        @Field("password") Password : String?
    ):Call<responadmin>

    annotation class FormUrlEncoded

    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("username") Username : String?,
        @Field("password") Password : String?
    ): Call<responadmin>

    annotation class Field(val value: String)
}