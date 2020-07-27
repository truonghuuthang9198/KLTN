package com.example.kltn.screen.home.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.FormatData
import com.example.kltn.screen.home.model.BookModel
import com.squareup.picasso.Picasso

class ShowMoreBookAdapter internal constructor(var showMoreBookModel: ArrayList<BookModel>)
    : RecyclerView.Adapter<ShowMoreBookAdapter.ShowMoreBookViewHolder>(){
    inner class ShowMoreBookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val salebook: TextView = itemView.findViewById(R.id.tv_salebook_showmore)
        val titleBook: TextView = itemView.findViewById(R.id.title_book_showmore_deal)
        val priceReducedBook: TextView = itemView.findViewById(R.id.priceReduced_book_showmore_deal)
        val priceBook: TextView = itemView.findViewById(R.id.price_book_showmore_deal)
        val imgBookDeal: ImageView = itemView.findViewById(R.id.img_book_showmore_deal)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowMoreBookViewHolder {
        val Context = parent.context
        val layoutInflater = LayoutInflater.from(Context)

        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_suggest,parent,false)
        val ShowMoreBookViewHolder = ShowMoreBookViewHolder(cellForRow)
        return ShowMoreBookViewHolder
    }

    override fun getItemCount() = showMoreBookModel.size

    override fun onBindViewHolder(holder: ShowMoreBookViewHolder, position: Int) {
        val current = showMoreBookModel[position]
        val giamgiahandle = Math.round(current.giamGia*100)
        holder.salebook.text = giamgiahandle.toString()+"%"
        holder.titleBook.text = current.tenSach
        val priceReducedfm = FormatData.formatMoneyVND(current.giaGiamDS)
        holder.priceReducedBook.text = priceReducedfm
        val priceBookfm = FormatData.formatMoneyVND(current.giaban)
        holder.priceBook.text = priceBookfm
        holder.priceBook.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        Picasso.get().load(current.hinhAnh).into(holder.imgBookDeal)
    }
}