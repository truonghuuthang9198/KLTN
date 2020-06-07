package com.example.kltn.screen.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.profile.adapter.InformationAdapter
import com.example.kltn.screen.profile.model.InformationModel

class InformationFragment: Fragment() {
    lateinit var recyclerviewIF: RecyclerView
    lateinit var informationAdapter: InformationAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_information_user, container, false)
        recyclerviewIF = view.findViewById(R.id.recyclerview_infomation_user)
        recyclerviewIF.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        setUpRecyclerview()
        return view

    }
    fun setUpRecyclerview()
    {
        val arrayList = ArrayList<InformationModel>()
        arrayList.add(InformationModel(R.drawable.ic_location,"Sổ địa chỉ","Quản lý địa chỉ",R.drawable.ic_next))
        arrayList.add(InformationModel(R.drawable.ic_history_buy,"Lịch sử mua hàng","Xem thêm",R.drawable.ic_next))
        arrayList.add(InformationModel(R.drawable.ic_favorite_black,"Sản phẩm yêu thích","Xem thêm",R.drawable.ic_next))
        arrayList.add(InformationModel(R.drawable.ic_ma_gioi_thieu,"Mã giới thiệu","Xem thêm",R.drawable.ic_next))
        arrayList.add(InformationModel(R.drawable.ic_swap_language,"Đổi ngôn ngữ","Xem thêm",R.drawable.ic_next))
        arrayList.add(InformationModel(R.drawable.ic_help,"Hỗ trợ","Xem thêm",R.drawable.ic_next))
        informationAdapter = InformationAdapter(activity!!,arrayList)
        recyclerviewIF.adapter = informationAdapter
    }
}