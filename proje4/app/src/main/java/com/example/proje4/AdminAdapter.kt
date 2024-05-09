package com.example.proje4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AdminAdapter(
    private var adminList: ArrayList<Admin>,
    private val onDeleteClickListener: (Admin) -> Unit
) : RecyclerView.Adapter<AdminAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idTextView: TextView = view.findViewById(R.id.idTextView)
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val deleteButton: Button = view.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.admin_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val admin = adminList[position]
        holder.idTextView.text = admin.id.toString()
        holder.nameTextView.text = admin.name

        holder.deleteButton.setOnClickListener { onDeleteClickListener(admin) }
    }

    override fun getItemCount(): Int {
        return adminList.size
    }

    fun updateData(newAdminList: List<Admin>) {
        adminList.clear()
        adminList.addAll(newAdminList)
        notifyDataSetChanged()
    }
}
