package com.example.kltn.screen.home.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Paint
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
import com.example.kltn.screen.home.`interface`.CallBackFragment
import com.example.kltn.screen.home.deals.ChildShowMoreDealFragment
import com.example.kltn.screen.home.deals.ShowMoreDealFragment
import com.example.kltn.screen.home.deals.ShowMoreDealFragment.Companion.arrayList
import com.example.kltn.screen.home.model.FilterModel
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class FilterAdapter internal constructor(
    var context: Context,
    var FilterModel: ArrayList<FilterModel>,
    var callBack : CallBackFragment
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
        val Context = parent.context
        val layoutInflater = LayoutInflater.from(Context)

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
            arrayList.forEach {
                it.choose = it.id == current.id
            }
            title = current.titleFilter
            callBack.onCallBack()
            callBack.onSetBackRecyclerView()
            notifyDataSetChanged()
        }

        if (current.choose) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.titleFilter.setTextColor(context.getColor(R.color.colorCheckFilter))
            }
            holder.imgCheckFilter.visibility = View.VISIBLE
        } else {
            holder.imgCheckFilter.visibility = View.GONE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.titleFilter.setTextColor(context.getColor(R.color.black))
            }
        }
        holder.titleFilter.text = current.titleFilter
    }
    fun reLoadFragment(tag: String) {
        var frg: Fragment? = null
        frg =(context as FragmentActivity).getSupportFragmentManager().findFragmentByTag(tag)
        val ft: FragmentTransaction = (context as FragmentActivity).getSupportFragmentManager().beginTransaction()
        ft.detach(frg!!)
        ft.attach(frg!!)
        ft.commit()
    }
}