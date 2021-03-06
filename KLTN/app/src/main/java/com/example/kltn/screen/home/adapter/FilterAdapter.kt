package com.example.kltn.screen.home.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.home.model.FilterModel
import com.example.kltn.screen.event.EventFireUtil
import com.example.kltn.screen.event.OnActionData
import kotlin.collections.ArrayList

class FilterAdapter internal constructor(
    var context: Context?,
    var FilterModel: ArrayList<FilterModel>, var onActionData: OnActionData<FilterModel>, var tabId: Int
) : RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {
    companion object {
        var title:String = "Bán Chạy Tuần"

    }
    inner class FilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleFilter: TextView = itemView.findViewById(R.id.title_filter)
        val imgCheckFilter: ImageView = itemView.findViewById(R.id.icon_check_filter)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val layoutInflater = LayoutInflater.from(context)

        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_filter, parent, false)
        val FilterViewHolder = FilterViewHolder(cellForRow)

        return FilterViewHolder
    }

    override fun getItemCount() = FilterModel.size

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val current = FilterModel[position]
        holder.imgCheckFilter.setImageResource(current.imgCheck)
        holder.imgCheckFilter.visibility = View.GONE

        holder.itemView.setOnClickListener {
                FilterModel.forEach {
                    it.choose = it.id == current.id
                    title = current.titleFilter
                    notifyDataSetChanged()
                    EventFireUtil.fireEvent(onActionData, current)
            }
        }

        if (current.choose) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.titleFilter.setTextColor(context!!.getColor(R.color.colorCheckFilter))
            }
            holder.imgCheckFilter.visibility = View.VISIBLE
        } else {
            holder.imgCheckFilter.visibility = View.GONE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.titleFilter.setTextColor(context!!.getColor(R.color.black))
            }
        }
        holder.titleFilter.text = current.titleFilter
    }
}