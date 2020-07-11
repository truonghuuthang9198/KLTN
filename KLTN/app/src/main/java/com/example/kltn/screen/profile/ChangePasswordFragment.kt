package com.example.kltn.screen.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.profile.adapter.FavoriteAdapter

class ChangePasswordFragment : Fragment() {
    lateinit var edt_nhapmkcu: EditText
    lateinit var edt_nhapmatkhaumoi: EditText
    lateinit var edt_xacnhanmkmoi: EditText
    lateinit var btn_back_doimatkhau: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_change_password, container, false)
        btn_back_doimatkhau = view.findViewById(R.id.btn_back_doimatkhau)
        edt_nhapmkcu = view.findViewById(R.id.edt_nhapmkcu)
        edt_nhapmatkhaumoi =view.findViewById(R.id.edt_nhapmatkhaumoi)
        edt_xacnhanmkmoi = view.findViewById(R.id.edt_xacnhanmkmoi)
        return view
    }
}