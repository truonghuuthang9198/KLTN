package com.example.kltn.screen.profile.adapter

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
import com.example.kltn.screen.profile.ChangePasswordFragment
import com.example.kltn.screen.profile.ManangerAddressFragment
import com.example.kltn.screen.profile.FavoriteFragment
import com.example.kltn.screen.profile.HistoryFragment
import com.example.kltn.screen.profile.model.InformationModel

class InformationAdapter internal constructor(var context: Context?, var InformationModel: ArrayList<InformationModel>) :
    RecyclerView.Adapter<InformationAdapter.InformationViewHolder>() {
    inner class InformationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icNameIF: ImageView = itemView.findViewById(R.id.ic_name_infomation)
        val nameIF: TextView = itemView.findViewById(R.id.tv_name_information)
        val moreIF: TextView = itemView.findViewById(R.id.tv_more_information)
        val icMore: ImageView = itemView.findViewById(R.id.ic_more_information)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InformationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_infomation_user, parent, false)
        val InformationViewHolder = InformationViewHolder(cellForRow)
        return InformationViewHolder
    }

    override fun getItemCount() = InformationModel.size

    override fun onBindViewHolder(holder: InformationViewHolder, position: Int) {
        val current = InformationModel[position]
        holder.itemView.setOnClickListener {
            when (current.id) {
                1 -> {
                    loadFragment(ManangerAddressFragment(),"AddAddressFragment")
                }
                2 -> {
                    loadFragment(HistoryFragment(),"HistoryFragment")
                }
                3 -> {
                    loadFragment(FavoriteFragment(), "ShowMoreDealFragment")
                }
                4 -> {

                }
                5 -> {
                    loadFragment(ChangePasswordFragment(),"ChangePasswordFragment")
                }
                6 -> {

                }
            }
        }
        holder.icNameIF.setImageResource(current.icNameIF)
        holder.nameIF.text = current.nameIF
        holder.moreIF.text = current.moreIF
        holder.icMore.setImageResource(current.icMore)
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