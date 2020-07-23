package com.example.kltn.screen.home.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.home.model.CategoryDetailModel

class CategoryDetailAdapter internal constructor(
    var context: Context?,
    var CategoryDetailModel: ArrayList<CategoryDetailModel>,
    var maCT:String
) : RecyclerView.Adapter<CategoryDetailAdapter.CategoryDetailViewHolder>() {
    inner class CategoryDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleCategory: TextView = itemView.findViewById(R.id.tv_category_detail)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDetailViewHolder {
        val Context = parent.context
        val layoutInflater = LayoutInflater.from(Context)

        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_category_detail, parent, false)
        val CategoryDetailViewHolder = CategoryDetailViewHolder(cellForRow)

        return CategoryDetailViewHolder
    }

    override fun getItemCount() = CategoryDetailModel.size

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: CategoryDetailViewHolder, position: Int) {
        val current = CategoryDetailModel[position]
        holder.titleCategory.text = current.title
        holder.itemView.setOnClickListener {

        }
    }
}