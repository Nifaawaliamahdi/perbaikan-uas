package com.informatika19100008.MhdNazori_rangking_sekolah.model

import com.google.gson.annotations.SerializedName

data class responkalkulator(

    @field:SerializedName("pesan")
    val pesan: Any? = null,

    @field:SerializedName("data")
    val data: List<Boolean?>? = null,

    @field:SerializedName("status")
    val status: String? = null,
    )
