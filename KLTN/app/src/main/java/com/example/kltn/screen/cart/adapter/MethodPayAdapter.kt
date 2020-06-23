package com.example.kltn.screen.cart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.cart.model.AddressShipModel
import com.example.kltn.screen.cart.model.MethodPayModel

class MethodPayAdapter internal constructor(var context: Context, var methodPayModel: ArrayList<MethodPayModel>) :
    RecyclerView.Adapter<MethodPayAdapter.MethodPayViewHolder>() {
    private var lastSelectedPosition = -1
    inner class MethodPayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameSdt: TextView = itemView.findViewById(R.id.tv_name_methodpay)
        val radioButton: RadioButton = itemView.findViewById(R.id.rdbtn_methodpay)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MethodPayViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.reyclerview_item_phuongthucthanhtoan, parent, false)
        val MethodPayViewHolder = MethodPayViewHolder(cellForRow)
        MethodPayViewHolder.itemView.setOnClickListener {
            lastSelectedPosition = MethodPayViewHolder.adapterPosition
            notifyDataSetChanged()
        }
        return MethodPayViewHolder
    }

    override fun getItemCount() = methodPayModel.size

    override fun onBindViewHolder(holder: MethodPayViewHolder, position: Int) {
        val current = methodPayModel[position]
        holder.nameSdt.text = current.name
        holder.radioButton.isChecked = (lastSelectedPosition == position)
        holder.radioButton.setOnClickListener {

        }
    }


}