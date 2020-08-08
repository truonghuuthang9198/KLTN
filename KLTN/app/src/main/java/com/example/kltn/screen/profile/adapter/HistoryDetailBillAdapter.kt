package com.example.kltn.screen.profile.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.DetailActivity
import com.example.kltn.R
import com.example.kltn.screen.FormatData
import com.example.kltn.screen.home.model.BookModel
import com.example.kltn.screen.profile.model.HistoryBillModel
import com.example.kltn.screen.profile.model.HistoryDetailBillModel
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class HistoryDetailBillAdapter internal constructor(var context: Context?, var listDetailBillHistory: ArrayList<BookModel>) :
    RecyclerView.Adapter<HistoryDetailBillAdapter.HistoryDetailBillViewHolder>() {
    inner class HistoryDetailBillViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img_sach_detailBill: ImageView = itemView.findViewById(R.id.img_sach_detailBill)
        val tv_tensach_detailbill: TextView = itemView.findViewById(R.id.tv_tensach_detailbill)
        val tv_giatien_detailbill: TextView = itemView.findViewById(R.id.tv_giatien_detailbill)
        val tv_soluong_detail_bill: TextView = itemView.findViewById(R.id.tv_soluong_detail_bill)
        val btn_mualai_detailbill: Button = itemView.findViewById(R.id.btn_mualai_detailbill)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryDetailBillViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_detail_history_bill, parent, false)
        val HistoryDetailBillViewHolder = HistoryDetailBillViewHolder(cellForRow)
        return HistoryDetailBillViewHolder
    }

    override fun getItemCount() = listDetailBillHistory.size

    override fun onBindViewHolder(holder: HistoryDetailBillViewHolder, position: Int) {
        val current = listDetailBillHistory[position]
        holder.tv_tensach_detailbill.text = current.tenSach
        holder.tv_soluong_detail_bill.text = current.soLuong.toString()
        holder.tv_giatien_detailbill.text = FormatData.formatMoneyVND(current.giaGiamDS)
        Picasso.get().load(current.hinhAnh).into(holder.img_sach_detailBill)
        holder.btn_mualai_detailbill.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("deal",current)
            context?.startActivity(intent)
        }
    }


}