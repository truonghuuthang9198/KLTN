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

class ShowMoreDealAdapter internal constructor(var showMoreBookModel: ArrayList<BookModel>)
    : RecyclerView.Adapter<ShowMoreDealAdapter.ShowMoreDealViewHolder>(){
    inner class ShowMoreDealViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val salebook: TextView = itemView.findViewById(R.id.tv_salebook_showmore)
        val titleBook: TextView = itemView.findViewById(R.id.title_book_showmore_deal)
        val priceReducedBook: TextView = itemView.findViewById(R.id.priceReduced_book_showmore_deal)
        val priceBook: TextView = itemView.findViewById(R.id.price_book_showmore_deal)
        val imgBookDeal: ImageView = itemView.findViewById(R.id.img_book_showmore_deal)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowMoreDealViewHolder {
        val Context = parent.context
        val layoutInflater = LayoutInflater.from(Context)
        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_show_more_deal,parent,false)

        val ShowMoreDealViewHolder = ShowMoreDealViewHolder(cellForRow)
        ShowMoreDealViewHolder.itemView.setOnClickListener {
            var dealModel = showMoreBookModel.get(ShowMoreDealViewHolder.adapterPosition)
            val intent = Intent(Context, DetailActivity::class.java)
            intent.putExtra("deal",dealModel)
            Context.startActivity(intent)
        }
        return ShowMoreDealViewHolder
    }

    override fun getItemCount() = showMoreBookModel.size

    override fun onBindViewHolder(holder: ShowMoreDealViewHolder, position: Int) {
        val current = showMoreBookModel[position]
        val giamgiahandle = Math.round(current.giamGia*100)
        holder.salebook.text = giamgiahandle.toString()+"%"
        holder.titleBook.text = current.tenSach
        val priceReducedfm = FormatData.formatMoneyVND(current.giaGiamDS)
        holder.priceReducedBook.text = priceReducedfm
        val priceBookfm =FormatData.formatMoneyVND(current.giaban)
        holder.priceBook.text = priceBookfm
        holder.priceBook.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        Picasso.get().load(current.hinhAnh).into(holder.imgBookDeal)
    }
}