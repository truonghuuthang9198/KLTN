package com.example.kltn.screen.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.profile.adapter.FavoriteAdapter

class AddressFragment : Fragment() {
    lateinit var btnBack_Address: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_address, container, false)
        btnBack_Address = view.findViewById(R.id.btn_back_address)
        btnBack_Address.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }
        return view
    }
}