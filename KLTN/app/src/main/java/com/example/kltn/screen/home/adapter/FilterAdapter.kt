package com.example.kltn.screen.home.adapter

import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.home.model.FilterModel
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class FilterAdapter internal constructor(var FilterModel: ArrayList<FilterModel>)
    : RecyclerView.Adapter<FilterAdapter.FilterViewHolder>(){
    inner class FilterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val titleFilter: TextView = itemView.findViewById(R.id.title_filter)
        val imgCheckFilter: ImageView = itemView.findViewById(R.id.icon_check_filter)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val Context = parent.context
        val layoutInflater = LayoutInflater.from(Context)

        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_filter,parent,false)
        val FilterViewHolder = FilterViewHolder(cellForRow)
//        DealViewHolder.itemView.setOnClickListener {
//            var dealModel = FilterModel.get(DealViewHolder.adapterPosition)
//            val intent = Intent(Context, DetailActivity::class.java)
//            intent.putExtra("deal",dealModel)
//            Context.startActivity(intent)
//        }
        return FilterViewHolder
    }

    override fun getItemCount() = FilterModel.count()

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val current = FilterModel[position]
        var check:Int = 0
        holder.imgCheckFilter.setImageResource(current.imgCheck)
        holder.itemView.setOnClickListener {
            if(check == 0) {
                holder.imgCheckFilter.visibility = View.VISIBLE
                check = 1
            }
            else
            {
                holder.imgCheckFilter.visibility = View.GONE
            }
        }
        holder.titleFilter.text = current.titleFilter
    }
}