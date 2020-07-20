package com.example.kltn.screen.profile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.profile.model.ManangerAddressModel
import com.example.kltn.screen.event.EventFireUtil
import com.example.kltn.screen.event.OnActionData
import kotlin.collections.ArrayList

class ManangerAddressAdapter internal constructor(var context: Context?, var listAddresses: ArrayList<ManangerAddressModel>, var onActionData: OnActionData<ManangerAddressModel>) :
    RecyclerView.Adapter<ManangerAddressAdapter.AddAddressViewHolder>() {
    inner class AddAddressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_addaddress: TextView = itemView.findViewById(R.id.tv_diachi_addaddress)
        val tv_sodienthoai: TextView = itemView.findViewById(R.id.tv_sdt_addaddress)
        val tv_address_payship: TextView = itemView.findViewById(R.id.tv_address_payship)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddAddressViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_addaddress, parent, false)
        val AddAddressViewHolder = AddAddressViewHolder(cellForRow)
        return AddAddressViewHolder
    }

    override fun getItemCount() = listAddresses.size

    override fun onBindViewHolder(holder: AddAddressViewHolder, position: Int) {
        val current = listAddresses[position]
        holder.tv_addaddress.text = current.address+", "+current.xa+", "+current.quan+", "+current.tinh+", Việt Nam"
        holder.tv_sodienthoai.text = current.sdt
        if(current.checkaddress == 1) {
            holder.tv_address_payship.text = "Địa chỉ giao hàng mặc định"
        }else if(current.checkaddress == 2)
        {
            holder.tv_address_payship.text = "Địa chỉ thanh toán mặc định"
        }else
        {
            holder.tv_address_payship.text = "Địa chỉ khác"
        }
        holder.itemView.setOnClickListener {
            EventFireUtil.fireEvent(onActionData,current)
        }
    }

    private fun loadFragment(fragment: Fragment?, tag: String): Boolean {
        if (fragment != null) {
            (context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment, tag)
                .addToBackStack(null)
                .commit()
            return true
        }
        return false
    }

}