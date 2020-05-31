package com.example.kltn.screen.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.home.model.MenuModel

import com.google.android.material.bottomsheet.BottomSheetDialog
import de.hdodenhof.circleimageview.CircleImageView

class MenuAdapter internal constructor(var MenuModel: ArrayList<MenuModel>)
    : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>(){
    private lateinit var bottomDialog : BottomSheetDialog
    private lateinit var bottomDialogView : View
    inner class MenuViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val name: TextView = itemView.findViewById(R.id.tv_name)
        val iconMenu: ImageView = itemView.findViewById(R.id.menu_icon)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val cellForRow = layoutInflater.inflate(R.layout.recycleview_menu_item,parent,false)
        val MenuViewHolder = MenuViewHolder(cellForRow)
        return MenuViewHolder
    }

    override fun getItemCount() = MenuModel.count()

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val current = MenuModel[position]
        holder.name.text = current.name
        holder.iconMenu.setImageResource(current.icon)
    }
}