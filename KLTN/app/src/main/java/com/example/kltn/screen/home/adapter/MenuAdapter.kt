package com.example.kltn.screen.home.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.DetailActivity
import com.example.kltn.MainActivity
import com.example.kltn.R
import com.example.kltn.screen.home.deals.ShowMoreDealFragment
import com.example.kltn.screen.home.model.MenuModel
import com.example.kltn.screen.suggest.SuggestFragment

import com.google.android.material.bottomsheet.BottomSheetDialog
import de.hdodenhof.circleimageview.CircleImageView

class MenuAdapter internal constructor(var context: Context, var MenuModel: ArrayList<MenuModel>) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_name)
        val iconMenu: ImageView = itemView.findViewById(R.id.menu_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recycleview_menu_item, parent, false)
        val MenuViewHolder = MenuViewHolder(cellForRow)
        return MenuViewHolder
    }

    override fun getItemCount() = MenuModel.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val current = MenuModel[position]
        holder.itemView.setOnClickListener {
            when (current.id) {
                3 -> {
                    loadFragment(ShowMoreDealFragment(), "ShowMoreDealFragment")
                }
                4 -> {
                    loadFragment(SuggestFragment(),"SuggestFragment")
                }
            }
        }
        holder.name.text = current.name
        holder.iconMenu.setImageResource(current.icon)
    }

    private fun loadFragment(fragment: Fragment?, tag: String): Boolean {
        if (fragment != null) {
            (context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment, tag)
                .addToBackStack(null)
                .commit()
            return true
        }
        return false
    }

}