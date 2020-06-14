package com.example.kltn.screen.profile

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.profile.adapter.FavoriteAdapter
import com.example.kltn.screen.profile.adapter.InformationAdapter
import com.example.kltn.screen.profile.model.FavoriteModel

class FavoriteFragment : Fragment() {
    lateinit var recyclerViewFavorite: RecyclerView
    lateinit var favoriteAdapter: FavoriteAdapter
    lateinit var btnBack_Favorite: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        recyclerViewFavorite = view.findViewById(R.id.recyclerview_favorite)
        recyclerViewFavorite.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        addList()
        btnBack_Favorite = view.findViewById(R.id.btn_back_favorite)
        btnBack_Favorite.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }
        return view
    }

    fun addList() {
        var arrayListFavorite: ArrayList<FavoriteModel> = ArrayList<FavoriteModel>()
        arrayListFavorite.add(
            FavoriteModel(
                R.drawable.vd_sach,
                "The fellowship of the Ring (The Lord of The Rings,Book1)",
                26000.00
            )
        )
        arrayListFavorite.add(
            FavoriteModel(
                R.drawable.vd_sach,
                "The fellowship of the Ring (The Lord of The Rings,Book1)",
                26000.00
            )
        )
        arrayListFavorite.add(
            FavoriteModel(
                R.drawable.vd_sach,
                "The fellowship of the Ring (The Lord of The Rings,Book1)",
                26000.00
            )
        )
        favoriteAdapter = FavoriteAdapter(activity!!, arrayListFavorite)
        recyclerViewFavorite.adapter = favoriteAdapter
    }
}