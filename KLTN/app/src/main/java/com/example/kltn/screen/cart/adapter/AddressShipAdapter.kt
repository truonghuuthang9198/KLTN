package com.example.kltn.screen.cart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.cart.model.AddressShipModel

class AddressShipAdapter internal constructor(var context: Context?, var addressShipModel: ArrayList<AddressShipModel>) :
    RecyclerView.Adapter<AddressShipAdapter.AddressShipViewHolder>() {
    private var lastSelectedPosition = -1
    inner class AddressShipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameSdt: TextView = itemView.findViewById(R.id.tv_name_sdt_diachigiaohang)
        val address: TextView = itemView.findViewById(R.id.tv_diachi_diachigiaohang)
        val radioButton: RadioButton = itemView.findViewById(R.id.rdbtn_offer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressShipViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_diachigiaohang, parent, false)
        val AddressShipViewHolder = AddressShipViewHolder(cellForRow)
        AddressShipViewHolder.itemView.setOnClickListener {
            lastSelectedPosition = AddressShipViewHolder.adapterPosition
            notifyDataSetChanged()
        }
        return AddressShipViewHolder
    }

    override fun getItemCount() = addressShipModel.size

    override fun onBindViewHolder(holder: AddressShipViewHolder, position: Int) {
        val current = addressShipModel[position]
        holder.nameSdt.text = current.name
        holder.address.text = current.address
        holder.radioButton.isChecked = (lastSelectedPosition == position)
        holder.radioButton.setOnClickListener {
        }
    }


}