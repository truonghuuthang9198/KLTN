package com.example.kltn.screen.home.adapter

import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.DetailActivity
import com.example.kltn.R
import com.example.kltn.screen.FormatData
import com.example.kltn.screen.home.model.BookModel
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class ChildrenBookAdapter internal constructor(var ChildrenBookModel: ArrayList<BookModel>)
    : RecyclerView.Adapter<ChildrenBookAdapter.ChildBookViewHolder>(){
    inner class ChildBookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val titleBook: TextView = itemView.findViewById(R.id.title_bestbook)
        val priceReducedBook: TextView = itemView.findViewById(R.id.priceReduced_bestbook)
        val priceBook: TextView = itemView.findViewById(R.id.price_bestbook)
        val imgBookDeal: ImageView = itemView.findViewById(R.id.img_bestbook)
        val salebook: TextView = itemView.findViewById(R.id.tv_salebook_bestbook)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildBookViewHolder {
        val Context = parent.context
        val layoutInflater = LayoutInflater.from(Context)

        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_bestbook,parent,false)
        val ChildBookViewHolder = ChildBookViewHolder(cellForRow)
        ChildBookViewHolder.itemView.setOnClickListener {
            var dealModel = ChildrenBookModel.get(ChildBookViewHolder.adapterPosition)
            val intent = Intent(Context, DetailActivity::class.java)
            intent.putExtra("deal",dealModel)
            Context.startActivity(intent)
        }
        return ChildBookViewHolder
    }

    override fun getItemCount() = ChildrenBookModel.size

    override fun onBindViewHolder(holder: ChildBookViewHolder, position: Int) {
        val current = ChildrenBookModel[position]
        val giamgiahandle = Math.round(current.giamGia*100)
        holder.salebook.text = giamgiahandle.toString()+"%"
        holder.titleBook.text = current.tenSach
        holder.priceReducedBook.text = FormatData.formatMoneyVND(current.giaGiamDS)
        val priceBookfm =FormatData.formatMoneyVND(current.giaban)
        holder.priceBook.text = priceBookfm
        holder.priceBook.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        Picasso.get().load(current.hinhAnh).into(holder.imgBookDeal)
    }
}