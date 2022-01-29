package com.informatika19100019.nifaawali.kalkulator.model

import com.google.gson.annotations.SerializedName

data class kalkulator(

    @field:SerializedName( "pesan")
    val pesan: String? = null,

    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("status")
    val status: Boolean? = null
)

annotation class SerializedName(val value: String)

data class DataItem(

    @field:SerializedName("angka_1")
    val angka_1: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("angka_2")
    val angka_2: String? =null,
    
    @field:SerializedName("simbol")
     val simbol: String? =null,

      @field:SerializedName("hasil")
       val hasil : String? =null
)