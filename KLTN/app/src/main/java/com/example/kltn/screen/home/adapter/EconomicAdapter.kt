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

class EconomicAdapter internal constructor(var listEconomic: ArrayList<BookModel>)
    : RecyclerView.Adapter<EconomicAdapter.EconomicViewHolder>(){
    inner class EconomicViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val salebook: TextView = itemView.findViewById(R.id.tv_salebook)
        val titleBookDeal: TextView = itemView.findViewById(R.id.title_book_deal)
        val priceReducedBook: TextView = itemView.findViewById(R.id.priceReduced_book_deal)
        val priceBook: TextView = itemView.findViewById(R.id.price_book_deal)
        val imgBookDeal: ImageView = itemView.findViewById(R.id.img_book_deal)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EconomicViewHolder {
        val Context = parent.context
        val layoutInflater = LayoutInflater.from(Context)

        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_deal,parent,false)
        val EconomicViewHolder = EconomicViewHolder(cellForRow)
        EconomicViewHolder.itemView.setOnClickListener {
            var dealModel = listEconomic.get(EconomicViewHolder.adapterPosition)
            val intent = Intent(Context, DetailActivity::class.java)
            intent.putExtra("deal",dealModel)
            Context.startActivity(intent)
        }
        return EconomicViewHolder
    }

    override fun getItemCount() = listEconomic.size

    override fun onBindViewHolder(holder: EconomicViewHolder, position: Int) {
        val current = listEconomic[position]
        val giamgiahandle = Math.round(current.giamGia*100)
        holder.salebook.text = giamgiahandle.toString()+"%"
        holder.titleBookDeal.text = current.tenSach
        holder.priceReducedBook.text = FormatData.formatMoneyVND(current.giaGiamDS)
        holder.priceBook.text = FormatData.formatMoneyVND(current.giaban)
        holder.priceBook.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        Picasso.get().load(current.hinhAnh).into(holder.imgBookDeal)
    }

}