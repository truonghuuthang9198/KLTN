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
import com.example.kltn.screen.home.model.BestBookModel
import com.example.kltn.screen.home.model.BookModel
import com.example.kltn.screen.home.model.SGKModel
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class BestBookAdapter internal constructor(var BestBookModel: ArrayList<BookModel>)
    : RecyclerView.Adapter<BestBookAdapter.BestBookViewHolder>(){
    inner class BestBookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val titleBook: TextView = itemView.findViewById(R.id.title_bestbook)
        val priceReducedBook: TextView = itemView.findViewById(R.id.priceReduced_bestbook)
        val priceBook: TextView = itemView.findViewById(R.id.price_bestbook)
        val imgBookDeal: ImageView = itemView.findViewById(R.id.img_bestbook)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestBookViewHolder {
        val Context = parent.context
        val layoutInflater = LayoutInflater.from(Context)

        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_bestbook,parent,false)
        val BestBookViewHolder = BestBookViewHolder(cellForRow)
        BestBookViewHolder.itemView.setOnClickListener {
            var dealModel = BestBookModel.get(BestBookViewHolder.adapterPosition)
            val intent = Intent(Context, DetailActivity::class.java)
            intent.putExtra("deal",dealModel)
            Context.startActivity(intent)
        }
        return BestBookViewHolder
    }

    override fun getItemCount() = BestBookModel.size

    override fun onBindViewHolder(holder: BestBookViewHolder, position: Int) {
        val current = BestBookModel[position]
        holder.titleBook.text = current.tenSach
        holder.priceReducedBook.text = FormatData.formatMoneyVND(current.giaGiamDS)
        holder.priceBook.text = FormatData.formatMoneyVND(current.giaban)
        holder.priceBook.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        Picasso.get().load(current.hinhAnh).into(holder.imgBookDeal)

    }
}