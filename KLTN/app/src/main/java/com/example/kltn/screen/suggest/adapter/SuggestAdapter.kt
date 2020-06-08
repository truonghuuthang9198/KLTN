package com.example.kltn.screen.suggest.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.home.model.ShowMoreDealModel
import com.example.kltn.screen.suggest.model.SuggestModel
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class SuggestAdapter internal constructor(var SuggestModel: ArrayList<SuggestModel>)
    : RecyclerView.Adapter<SuggestAdapter.SuggestViewHolder>(){
    inner class SuggestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val titleBook: TextView = itemView.findViewById(R.id.title_book_suggest)
        val priceReducedBook: TextView = itemView.findViewById(R.id.priceReduced_book_suggest)
        val priceBook: TextView = itemView.findViewById(R.id.price_book_suggest)
        val imgBookDeal: ImageView = itemView.findViewById(R.id.img_book_suggest)
        val star: RatingBar = itemView.findViewById(R.id.star_book_suggest)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestViewHolder {
        val Context = parent.context
        val layoutInflater = LayoutInflater.from(Context)

        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_suggest,parent,false)
        val SuggestViewHolder = SuggestViewHolder(cellForRow)
        return SuggestViewHolder
    }

    override fun getItemCount() = SuggestModel.size

    override fun onBindViewHolder(holder: SuggestViewHolder, position: Int) {
        val current = SuggestModel[position]
        holder.titleBook.text = current.titleBookSuggest
        val localVN = Locale("vi","VN")
        val numberFormat = NumberFormat.getCurrencyInstance(localVN)
        val priceReducedfm =numberFormat.format(current.priceReducedSuggest)
        holder.priceReducedBook.text = priceReducedfm
        val priceBookfm =numberFormat.format(current.priceSuggest)
        holder.priceBook.text = priceBookfm
        holder.priceBook.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.imgBookDeal.setImageResource(current.imgBookSuggest)
        holder.star.rating = current.starBookSuggest.toFloat()
    }
}