package com.example.kltn.screen.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.home.deal.ShowMoreDealFragment
import com.example.kltn.screen.home.model.MenuModel
import com.example.kltn.screen.event.EventFireUtil
import com.example.kltn.screen.event.OnActionNotify
import com.example.kltn.screen.home.CategoryFragment
import com.example.kltn.screen.home.ShowMoreTopicFragment


class MenuAdapter internal constructor(var context: Context?, var MenuModel: ArrayList<MenuModel>,var onActionNotify: OnActionNotify) :
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
                1 -> {
                    loadFragment(CategoryFragment(),"CategoryFragment")
                }
                2 -> {

                }
                3 -> {
                    loadFragment(ShowMoreDealFragment(), "ShowMoreDealFragment")
                }
                4 -> {
                    EventFireUtil.fireEvent(onActionNotify)
                }
                5->{
                    loadFragment(ShowMoreTopicFragment("TL0017"),"ShowMoreTopicDC")
                }
                6->{
                    loadFragment(ShowMoreTopicFragment("TL0015"),"ShowMoreTopicVPP")
                }
                7->{
                    loadFragment(ShowMoreTopicFragment("TL001"),"ShowMoreTopicVH")
                }
                8->{
                    loadFragment(ShowMoreTopicFragment("TL003"),"ShowMoreTopicTN")
                }
                9->{
                    loadFragment(ShowMoreTopicFragment("TL0016"),"ShowMoreTopicTLKN")
                }
                10->{
                    loadFragment(ShowMoreTopicFragment("TL002"),"ShowMoreTopicKT")
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