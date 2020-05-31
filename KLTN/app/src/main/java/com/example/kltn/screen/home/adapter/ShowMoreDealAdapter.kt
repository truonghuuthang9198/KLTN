package com.example.kltn.screen.home.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.home.model.SGKModel
import com.example.kltn.screen.home.model.ShowMoreDealModel
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class ShowMoreDealAdapter internal constructor(var ShowMoreDealModel: ArrayList<ShowMoreDealModel>)
    : RecyclerView.Adapter<ShowMoreDealAdapter.ShowMoreDealViewHolder>(){
    inner class ShowMoreDealViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
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
        return ShowMoreDealViewHolder
    }

    override fun getItemCount() = ShowMoreDealModel.size

    override fun onBindViewHolder(holder: ShowMoreDealViewHolder, position: Int) {
        val current = ShowMoreDealModel[position]
        holder.titleBook.text = current.titleBookSMDeal
        val localVN = Locale("vi","VN")
        val numberFormat = NumberFormat.getCurrencyInstance(localVN)
        val priceReducedfm =numberFormat.format(current.priceReducedSMDeal)
        holder.priceReducedBook.text = priceReducedfm
        val priceBookfm =numberFormat.format(current.priceSMDeal)
        holder.priceBook.text = priceBookfm
        holder.priceBook.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.imgBookDeal.setImageResource(current.imgBookSMDeal)

    }
}