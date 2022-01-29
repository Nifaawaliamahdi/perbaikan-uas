package com.informatika19100019.nifaawali.kalkulator

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.informatika19100008.MhdNazori_rangking_sekolah.model.responkalkulator
import com.informatika19100019.nifaawali.kalkulator.adapter.ListContent
import com.informatika19100019.nifaawali.kalkulator.model.kalkulator
import com.informatika19100019.nifaawali.kalkulator.network.koneksi
import kotlinx.android.synthetic.main.activity_update_data.*
import kotlinx.android.synthetic.main.activity_update_data.et_jumlah_barang
import kotlinx.android.synthetic.main.activity_update_data.et_nama_barang
import kotlinx.android.synthetic.main.activity_update_data.rv_data_barang
import kotlinx.android.synthetic.main.activity_update_data.toolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateDataActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data)
        toolbar.title = "UPDATE DATA"
        toolbar.setTitleTextColor(Color.WHITE)

        val i = intent
        val id = i.getStringExtra("ID")
        val angka_1 = i.getStringExtra("angka_1")
        val angka_2 = i.getStringExtra("angka_2")
        val simbol = i.getStringExtra("simbol")
        val hasil = i.getStringExtra("hasil")

        et_angka_1.setText(angka_1)
        et_angka_2.setText(angka_2)
        et_simbol.setText(simbol)
        et_hasil.setText(hasil)

        btn_submit.setOnClickListener {
            val etangka_1 = et_angka_1.text
            val et_angka_2 = et_angka_2.text
            val etsimbol= et_simbol.text
            val et_hasil = et_hasil.text

            if (etangka_1.isEmpty()){
                Toast.makeText(this@UpdateDataActivity, "angka Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            }else if (etangka_2.isEmpty()){
                Toast.makeText(this@UpdateDataActivity, "angka Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            }else{
                actionData(etangka_1.toString(), et_angka_2.toString(), etsimbol.toString(),et_hasil.toString())
            }
        }
        btn_back.setOnClickListener {
            finish()
        }
        getData()
    }
    fun actionData(id : String, namaBarang : String, jmlBarang : String){
        koneksi.service.updateBarang(id, namaBarang, jmlBarang).enqueue(object : Callback<responkalkulator>{
            override fun onFailure(call: Call<responkalkulator>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<responkalkulator>,
                response: Response<responkalkulator>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(this@UpdateDataActivity, "data berhasil diupdate", Toast.LENGTH_LONG).show()
                    getData()
                }
            }
        })
    }
    fun getData(){
        koneksi.service.getBarang().enqueue(object : Callback<kalkulator>{
            override fun onFailure(call: Call<kalkulator>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<kalkulator>,
                response: Response<kalkulator>
            ) {
                if (response.isSuccessful){
                    val dataBody = response.body()
                    val datacontent = dataBody!!.data

                    val rvAdapter = ListContent(datacontent, this@UpdateDataActivity, "UpdateDataActivity")


                    rv_data_barang.apply {
                        adapter = rvAdapter
                        layoutManager = LinearLayoutManager(this@UpdateDataActivity)
                    }

                    }
                }

        })
    }
}