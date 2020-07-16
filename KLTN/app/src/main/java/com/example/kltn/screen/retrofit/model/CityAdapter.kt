package com.example.kltn.screen.retrofit.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.event.EventFireUtil
import com.example.kltn.screen.event.OnActionData
import kotlin.collections.ArrayList

class CityAdapter internal constructor(val listCity: ArrayList<CityModel>,var onActionData: OnActionData<CityModel>): RecyclerView.Adapter<CityAdapter.CityViewHolder>(){
    inner class CityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val nameCity: TextView = itemView.findViewById(R.id.tv_city_name)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val Context = parent.context
        val layoutInflater = LayoutInflater.from(Context)
        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_city,parent,false)
        val CityViewHolder = CityViewHolder(cellForRow)
        return CityViewHolder
    }

    override fun getItemCount() = listCity.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val current = listCity[position]
        holder.nameCity.text = current.cityname
        holder.itemView.setOnClickListener {
            EventFireUtil.fireEvent(onActionData,current)
        }
    }
}