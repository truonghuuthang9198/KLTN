package com.example.kltn.screen.cart.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.event.EventFireUtil
import com.example.kltn.screen.event.OnActionData
import com.example.kltn.screen.home.model.FilterModel
import com.example.kltn.screen.profile.model.ManangerAddressModel


class AddressShipAdapter internal constructor(var context: Context?, var addressShipModel: ArrayList<ManangerAddressModel>,var onActionData: OnActionData<ManangerAddressModel>) :
    RecyclerView.Adapter<AddressShipAdapter.AddressShipViewHolder>() {
    private var lastSelectedPosition = 0
    inner class AddressShipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameSdt: TextView = itemView.findViewById(R.id.tv_name_sdt_diachigiaohang)
        val address: TextView = itemView.findViewById(R.id.tv_diachi_diachigiaohang)
        val radioButton: RadioButton = itemView.findViewById(R.id.rdbtn_offer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressShipViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_diachigiaohang, parent, false)
        val AddressShipViewHolder = AddressShipViewHolder(cellForRow)
//        AddressShipViewHolder.itemView.setOnClickListener {
//            lastSelectedPosition = AddressShipViewHolder.adapterPosition
//        }
        return AddressShipViewHolder
    }

    override fun getItemCount() = addressShipModel.size

    override fun onBindViewHolder(holder: AddressShipViewHolder, position: Int) {

        val current = addressShipModel[position]
        holder.nameSdt.text = current.ho +" "+ current.ten + " - " + current.sdt
        holder.address.text = current.address +", "+current.xa+", "+current.quan+", "+current.tinh+", Việt Nam"
//        EventFireUtil.fireEvent(onActionData, addressShipModel[0])
        holder.itemView.setOnClickListener {
            EventFireUtil.fireEvent(onActionData, current)
            lastSelectedPosition = position
            notifyDataSetChanged()
        }
        holder.radioButton.isChecked = (lastSelectedPosition == position)
        holder.radioButton.setOnClickListener {
            EventFireUtil.fireEvent(onActionData, current)
            lastSelectedPosition = position
            notifyDataSetChanged()
        }
    }


}