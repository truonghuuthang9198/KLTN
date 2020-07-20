package com.example.kltn.screen.cart.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.FormatData
import com.example.kltn.screen.FormatData.Companion.formatMoneyVND
import com.example.kltn.screen.cart.CartFragment
import com.example.kltn.screen.cart.model.CartModel
import com.example.kltn.screen.cart.model.CheckBillModel
import com.example.kltn.screen.cart.roomdatabase.CartViewModel
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckListBillAdapter internal constructor(var context: Context?, var listBill: ArrayList<CheckBillModel>)
    : RecyclerView.Adapter<CheckListBillAdapter.CheckListBillViewHolder>(){
    inner class CheckListBillViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val tensachCheckBill: TextView = itemView.findViewById(R.id.tv_tensach_checkbill)
        val soluongCheckBill: TextView = itemView.findViewById(R.id.tv_soluong_checkbill)
        val giatienCheckBill: TextView = itemView.findViewById(R.id.tv_giatien_checkbill)
        val igmsachCheckBill: ImageView = itemView.findViewById(R.id.img_sach_checkbill)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckListBillViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.reyclerview_item_checkbill,parent,false)
        val CheckListBillViewHolder = CheckListBillViewHolder(cellForRow)
        return CheckListBillViewHolder
    }

    override fun getItemCount() = listBill.size
    override fun onBindViewHolder(holder: CheckListBillViewHolder, position: Int) {
        val current = listBill[position]
        holder.tensachCheckBill.text = current.tenSach
        holder.soluongCheckBill.text ="Số lượng: "+current.soLuong.toString()
        holder.giatienCheckBill.text = formatMoneyVND(current.giaTien)
        Picasso.get().load(current.image).into(holder.igmsachCheckBill)
    }
}