package com.example.kltn.screen.home.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.home.model.BestBookModel
import com.example.kltn.screen.home.model.SGKModel
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class BestBookAdapter internal constructor(var BestBookModel: ArrayList<BestBookModel>)
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
//        SGKViewHolder.itemView.setOnClickListener {
//            var dealModel = DealsModel.get(SGKViewHolder.adapterPosition)
//            val intent = Intent(Context, DetailActivity::class.java)
//            intent.putExtra("deal",dealModel)
//            Context.startActivity(intent)
//        }
        return BestBookViewHolder
    }

    override fun getItemCount() = BestBookModel.size

    override fun onBindViewHolder(holder: BestBookViewHolder, position: Int) {
        val current = BestBookModel[position]
        holder.titleBook.text = current.titleBestBook
        val localVN = Locale("vi","VN")
        val numberFormat = NumberFormat.getCurrencyInstance(localVN)
        val priceReducedfm =numberFormat.format(current.priceReducedBB)
        holder.priceReducedBook.text = priceReducedfm
        val priceBookfm =numberFormat.format(current.priceBB)
        holder.priceBook.text = priceBookfm
        holder.priceBook.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.imgBookDeal.setImageResource(current.imgBestBook)

    }
}