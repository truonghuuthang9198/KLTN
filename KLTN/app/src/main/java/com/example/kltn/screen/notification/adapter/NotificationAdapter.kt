package com.example.kltn.screen.notification.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.notification.model.NotificationModel

import com.google.android.material.bottomsheet.BottomSheetDialog

class NotificationAdapter internal constructor(var context: Context?,var NotificationModel: ArrayList<NotificationModel>)
    : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>(){

    inner class NotificationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val titleNotification: TextView = itemView.findViewById(R.id.title_notification)
        val contentNotification: TextView = itemView.findViewById(R.id.content_notification)
        val dateNotification: TextView = itemView.findViewById(R.id.date_notification)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recycleview_notification_item,parent,false)
        val NotificationViewHolder = NotificationViewHolder(cellForRow)
        return NotificationViewHolder
    }

    override fun getItemCount() = NotificationModel.size

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {

        val current = NotificationModel[position]
        holder.titleNotification.text = current.title
        holder.contentNotification.text = current.content
        holder.dateNotification.text = current.date
    }
}