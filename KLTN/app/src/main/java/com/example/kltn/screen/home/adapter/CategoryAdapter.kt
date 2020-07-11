package com.example.kltn.screen.home.adapter

import com.example.kltn.screen.home.model.CategoryModel
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.home.CategoryFragment
import com.example.kltn.screen.event.EventFireUtil
import com.example.kltn.screen.event.OnActionData
import kotlin.collections.ArrayList

class CategoryAdapter internal constructor(
    var context: Context?,
    var CategoryModel: ArrayList<CategoryModel>,var onActionData: OnActionData<CategoryModel>
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleCategory: TextView = itemView.findViewById(R.id.tv_category_topic)
        val view : View = itemView.findViewById(R.id.view_category_topic)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val Context = parent.context
        val layoutInflater = LayoutInflater.from(Context)

        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_category_topic, parent, false)
        val CategoryViewHolder = CategoryViewHolder(cellForRow)

        return CategoryViewHolder
    }

    override fun getItemCount() = CategoryModel.size

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val current = CategoryModel[position]
        holder.itemView.setOnClickListener {
            CategoryFragment.arrayListCategory.forEach {
                it.choose = it.id == current.id
            }
            EventFireUtil.fireEvent(onActionData,current)
            notifyDataSetChanged()
        }
        if (current.choose) {
            holder.titleCategory.setTextColor(context!!.getColor(R.color.colorPrimary))
            holder.view.setBackgroundColor(context!!.getColor(R.color.colorPrimary))
        } else {
            holder.titleCategory.setTextColor(context!!.getColor(R.color.Gray))
            holder.view.setBackgroundColor(context!!.getColor(R.color.white))
        }
        holder.titleCategory.text = current.titleCategory
    }
}