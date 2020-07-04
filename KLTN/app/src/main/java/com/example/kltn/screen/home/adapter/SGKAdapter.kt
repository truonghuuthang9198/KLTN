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
import com.example.kltn.screen.home.model.BookModel
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class SGKAdapter internal constructor(var SGKModel: ArrayList<BookModel>)
    : RecyclerView.Adapter<SGKAdapter.SGKViewHolder>(){
    inner class SGKViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val titleBook: TextView = itemView.findViewById(R.id.title_book_sgk)
        val priceReducedBook: TextView = itemView.findViewById(R.id.priceReduced_book_sgk)
        val priceBook: TextView = itemView.findViewById(R.id.price_book_sgk)
        val imgBookDeal: ImageView = itemView.findViewById(R.id.img_book_sgk)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SGKViewHolder {
        val Context = parent.context
        val layoutInflater = LayoutInflater.from(Context)

        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_sgk,parent,false)
        val SGKViewHolder = SGKViewHolder(cellForRow)
        SGKViewHolder.itemView.setOnClickListener {
            var sgkModel = SGKModel.get(SGKViewHolder.adapterPosition)
            val intent = Intent(Context, DetailActivity::class.java)
            intent.putExtra("deal",sgkModel)
            Context.startActivity(intent)
        }
        return SGKViewHolder
    }

    override fun getItemCount() = SGKModel.size

    override fun onBindViewHolder(holder: SGKViewHolder, position: Int) {
        val current = SGKModel[position]
        holder.titleBook.text = current.tenSach
        val localVN = Locale("vi","VN")
        val numberFormat = NumberFormat.getCurrencyInstance(localVN)
        val priceReducedfm =numberFormat.format(current.giaGiamDS)
        holder.priceReducedBook.text = priceReducedfm
        val priceBookfm =numberFormat.format(current.giaban)
        holder.priceBook.text = priceBookfm
        holder.priceBook.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        Picasso.get().load(current.hinhAnh).into(holder.imgBookDeal)
    }
}