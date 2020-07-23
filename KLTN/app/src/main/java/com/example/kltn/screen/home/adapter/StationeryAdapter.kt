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

class StationeryAdapter internal constructor(var listStationery: ArrayList<BookModel>)
    : RecyclerView.Adapter<StationeryAdapter.StationeryViewHolder>() {
    inner class StationeryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleBook: TextView = itemView.findViewById(R.id.title_book_sgk)
        val priceReducedBook: TextView = itemView.findViewById(R.id.priceReduced_book_sgk)
        val priceBook: TextView = itemView.findViewById(R.id.price_book_sgk)
        val imgBookDeal: ImageView = itemView.findViewById(R.id.img_book_sgk)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationeryViewHolder {
        val Context = parent.context
        val layoutInflater = LayoutInflater.from(Context)

        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_sgk, parent, false)
        val PsychologicalSkillViewHolder = StationeryViewHolder(cellForRow)
        PsychologicalSkillViewHolder.itemView.setOnClickListener {
            var book = listStationery.get(PsychologicalSkillViewHolder.adapterPosition)
            val intent = Intent(Context, DetailActivity::class.java)
            intent.putExtra("deal", book)
            Context.startActivity(intent)
        }
        return PsychologicalSkillViewHolder
    }

    override fun getItemCount() = listStationery.size

    override fun onBindViewHolder(holder: StationeryViewHolder, position: Int) {
        val current = listStationery[position]
        holder.titleBook.text = current.tenSach
        val priceReducedfm = FormatData.formatMoneyVND(current.giaGiamDS)
        holder.priceReducedBook.text = priceReducedfm
        val priceBookfm = FormatData.formatMoneyVND(current.giaban)
        holder.priceBook.text = priceBookfm
        holder.priceBook.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        Picasso.get().load(current.hinhAnh).into(holder.imgBookDeal)
    }
}