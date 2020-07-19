package com.example.kltn.screen

import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.DetailActivity
import com.example.kltn.R
import com.example.kltn.screen.home.model.BookModel
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class SearchAdapter internal constructor(var listSearch: ArrayList<BookModel>)
    : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){
    inner class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val titleBook: TextView = itemView.findViewById(R.id.title_book_suggest)
        val priceReducedBook: TextView = itemView.findViewById(R.id.priceReduced_book_suggest)
        val priceBook: TextView = itemView.findViewById(R.id.price_book_suggest)
        val imgBookDeal: ImageView = itemView.findViewById(R.id.img_book_suggest)
        val tvsale: TextView = itemView.findViewById(R.id.tv_salebook_suggest)
        val star: RatingBar = itemView.findViewById(R.id.star_book_suggest)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val Context = parent.context
        val layoutInflater = LayoutInflater.from(Context)

        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_suggest,parent,false)
        val SearchViewHolder = SearchViewHolder(cellForRow)
        SearchViewHolder.itemView.setOnClickListener {
            var dealModel = listSearch.get(SearchViewHolder.adapterPosition)
            val intent = Intent(Context, DetailActivity::class.java)
            intent.putExtra("deal",dealModel)
            Context.startActivity(intent)
        }
        return SearchViewHolder
    }

    override fun getItemCount() = listSearch.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val current = listSearch[position]
        val giamgiahandle = Math.round(current.giamGia*100)

        holder.tvsale.text = giamgiahandle.toString()+"%"
        holder.titleBook.text = current.tenSach
        val localVN = Locale("vi","VN")
        val numberFormat = NumberFormat.getCurrencyInstance(localVN)
        val priceReducedfm =numberFormat.format(current.giaGiamDS)
        holder.priceReducedBook.text = priceReducedfm
        val priceBookfm =numberFormat.format(current.giaban)
        holder.priceBook.text = priceBookfm
        holder.priceBook.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        Picasso.get().load(current.hinhAnh).into(holder.imgBookDeal)

        holder.star.numStars = 5
        holder.star.rating = current.soSao.toFloat()
    }
}