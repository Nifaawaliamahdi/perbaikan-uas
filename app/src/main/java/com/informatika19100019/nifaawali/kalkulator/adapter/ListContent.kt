package com.informatika19100019.nifaawali.kalkulator.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.informatika19100019.nifaawali_kalkulator.R
import com.informatika19100019.nifaawali_kalkulator.model.DataItem
import com.informatika19100019.kalkulator.InsertDataActivity
import com.informatika19100019.kalkulator.MainActivity
import com.informatika19100019.kalkulator.R
import com.informatika19100019.kalkulator.UpdateDataActivity
import com.informatika19100019.kalkulator.model.DataItem
import com.informatika19100019.kalkulator.model.ResponseActionBarang
import com.informatika19100019.kalkulator.network.koneksi
import com.informatika19100019.nifaawali.kalkulator.InsertDataActivity
import com.informatika19100019.nifaawali.kalkulator.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.security.auth.callback.Callback

class ListContent(val ldata : List<DataItem?>?, val context: Context, val kondisi : String) :
        RecyclerView.Adapter<ListContent.ViewHolder>() {
        class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
            val angka_1 = view.findViewById<TextView>(R.id.tv_angka_1)
            val angka_2 = view.findViewById<TextView>(R.id.tv_angka_2)
            val simbol = view.findViewById<TextView>(R.id.tv_simbol)
            val hasil= view.findViewById<TextView>(R.id.tv_hasil)
            val edit = view.findViewById<TextView>(R.id.tv_edit)
            val delete = view.findViewById<TextView>(R.id.tv_delete)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_barang, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return ldata!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = ldata?.get(position)
        holder.angka_1.text = model?.angka_1
        holder.angka_2.text = model?.angka_2
        holder.simbol.text = model?.simbol
        holder.hasil.text = model?.hasil
        holder.edit.setOnClickListener {
            val i = Intent(context, UpdateDataActivity::class.java)
            i.putExtra("ID", model?.id)
            i.putExtra("ANGKA1", model?.angka1)
            i.putExtra("ANGKA2", model?.angka2)
            i.putExtra("SIMBOL", model?.simbol)
            i.putExtra("HASIL", model?.hasil)
            context.startActivity(i)
        }
        holder.delete.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete" + model?.namaorang)
                .setMessage("Apakah Anda Ingin Mengahapus Data Ini?")
                .setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->

                    koneksi.service.deleteBarang(model?.id).enqueue(object :
                        Callback<responkalkulator> {
                        override fun onFailure(call: Call<responkalkulator>, t: Throwable) {
                            Log.d("pesan1", t.localizedMessage)
                        }

                        override fun onResponse(
                            call: Call<responkalkulator>,
                            response: Response<responkalkulator>
                        ) {
                            if(response.isSuccessful){
                                Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_LONG).show()
                                notifyDataSetChanged()
                                notifyItemRemoved(position)
                                notifyItemChanged(position)
                                notifyItemRangeChanged(position, ldata!!.size)

                                if(kondisi == " InsertDataActivity"){
                                    val activity = (context as InsertDataActivity)
                                    activity.getData()
                                }else if(kondisi == " UpdateDataActivity"){
                                    val activity = (context as UpdateDataActivity)
                                    activity.getData()
                                }else{
                                    val activity = (context as MainActivity)
                                    activity.getData()

                                }
                                Log.d("bpesan", response.body().toString())

                            }
                        }
                    })
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                    dialog.cancel()
                })
                .show()
        }
    }


}

class Response<T> {
    fun body(): Any {
        TODO("Not yet implemented")
    }

}

class RecyclerView {
    open class ViewHolder(value: View) {

    }

}
