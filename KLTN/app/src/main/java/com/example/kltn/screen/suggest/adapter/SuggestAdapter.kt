package com.example.kltn.screen.suggest.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
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

class SuggestAdapter internal constructor(var context: Context?,var SuggestModel: ArrayList<BookModel>)
    : RecyclerView.Adapter<SuggestAdapter.SuggestViewHolder>(){
    inner class SuggestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val titleBook: TextView = itemView.findViewById(R.id.title_book_suggest)
        val priceReducedBook: TextView = itemView.findViewById(R.id.priceReduced_book_suggest)
        val priceBook: TextView = itemView.findViewById(R.id.price_book_suggest)
        val imgBookDeal: ImageView = itemView.findViewById(R.id.img_book_suggest)
        val star: RatingBar = itemView.findViewById(R.id.star_book_suggest)
        val salebook: TextView = itemView.findViewById(R.id.tv_salebook_suggest)
        val btn_muangay:Button = itemView.findViewById(R.id.btn_muangay)
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
        val giamgiahandle = Math.round(current.giamGia*100)
        holder.salebook.text = giamgiahandle.toString()+"%"
        holder.titleBook.text = current.tenSach
        holder.priceReducedBook.text = FormatData.formatMoneyVND(current.giaGiamDS)
        holder.priceBook.text =  FormatData.formatMoneyVND(current.giaban)
        holder.priceBook.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        Picasso.get().load(current.hinhAnh).into(holder.imgBookDeal)
        holder.star.numStars = 5
        holder.star.rating = current.soSao.toFloat()
        holder.btn_muangay.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("deal",current)
            context?.startActivity(intent)
        }
    }
}