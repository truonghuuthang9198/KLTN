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

class NotificationAdapter internal constructor(var context: Context,var NotificationModel: ArrayList<NotificationModel>)
    : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>(){

    inner class NotificationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val imgNotification: ImageView = itemView.findViewById(R.id.img_notification)
        val nameNotification: TextView = itemView.findViewById(R.id.tv_notification_name)
        val btnMore: ImageView = itemView.findViewById(R.id.btn_more_notification)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recycleview_notification_item,parent,false)
        val NotificationViewHolder = NotificationViewHolder(cellForRow)
        return NotificationViewHolder
    }

    override fun getItemCount() = NotificationModel.size
    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {

        val current = NotificationModel[position]
        holder.imgNotification.setImageResource(current.image)
        holder.nameNotification.text = current.nameNotification
        holder.btnMore.setOnClickListener {
//day click vo thi show cai bottom sheet ra
            val customView = LayoutInflater.from(context).inflate(R.layout.bottomsheet_dialog_notification, null, false)
            val dialog = BottomSheetDialog(context)
            dialog.setContentView(customView)
            dialog.create()
            dialog.show()
        }
    }
}