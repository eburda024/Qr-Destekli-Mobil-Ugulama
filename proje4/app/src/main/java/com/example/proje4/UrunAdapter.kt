package com.example.proje4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class UrunAdapter(
    private var urunList: ArrayList<Urunler>,
    private val onDeleteClickListener: (Urunler) -> Unit
) : RecyclerView.Adapter<UrunAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idTextView:TextView =view.findViewById(R.id.idTextView)
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val descTextView: TextView = view.findViewById(R.id.descTextView)
        val deleteButton: Button = view.findViewById(R.id.deleteButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.urun_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val urun = urunList[position]
        holder.idTextView.text =urun.id.toString()
        holder.nameTextView.text = urun.name
        holder.descTextView.text = urun.desc


        holder.deleteButton.setOnClickListener { onDeleteClickListener(urun) }
    }

    override fun getItemCount(): Int {
        return urunList.size
    }
    fun updateData(newUrunList: List<Urunler>) {
        urunList.clear()
        urunList.addAll(newUrunList)
        notifyDataSetChanged()
    }



}
