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
import com.example.kltn.screen.home.model.DealsModel
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class DealAdapter internal constructor(var DealsModel: ArrayList<DealsModel>)
    : RecyclerView.Adapter<DealAdapter.DealViewHolder>(){
    inner class DealViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val titleBookDeal: TextView = itemView.findViewById(R.id.title_book_deal)
        val priceReducedBook: TextView = itemView.findViewById(R.id.priceReduced_book_deal)
        val priceBook: TextView = itemView.findViewById(R.id.price_book_deal)
        val imgBookDeal: ImageView = itemView.findViewById(R.id.img_book_deal)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealViewHolder {
        val Context = parent.context
        val layoutInflater = LayoutInflater.from(Context)

        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_deal,parent,false)
        val DealViewHolder = DealViewHolder(cellForRow)
        DealViewHolder.itemView.setOnClickListener {
            var dealModel = DealsModel.get(DealViewHolder.adapterPosition)
            val intent = Intent(Context,DetailActivity::class.java)
            intent.putExtra("deal",dealModel)
            Context.startActivity(intent)
        }
        return DealViewHolder
    }

    override fun getItemCount() = DealsModel.count()

    override fun onBindViewHolder(holder: DealViewHolder, position: Int) {
        val current = DealsModel[position]
        holder.titleBookDeal.text = current.titleBookDeal
        val localVN = Locale("vi","VN")
        val numberFormat = NumberFormat.getCurrencyInstance(localVN)
        val priceReducedfm =numberFormat.format(current.priceReduced)
        holder.priceReducedBook.text = priceReducedfm
        val priceBookfm =numberFormat.format(current.price)
        holder.priceBook.text = priceBookfm
        holder.priceBook.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.imgBookDeal.setImageResource(current.imgBookDeal)

    }
}