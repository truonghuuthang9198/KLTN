package com.example.kltn.screen.profile

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.profile.adapter.InformationAdapter
import com.example.kltn.screen.profile.model.InformationModel
import com.example.kltn.screen.retrofit.reponse.CheckLoginResponse
import com.example.kltn.screen.retrofit.reponse.LoginResponse

@Suppress("DEPRECATION")
class InformationFragment(val responseUser: LoginResponse): Fragment() {
    lateinit var recyclerviewIF: RecyclerView
    lateinit var informationAdapter: InformationAdapter
    lateinit var btnDangXuat: Button
    lateinit var tv_hoten_infomation: TextView
    lateinit var tv_email_infomation: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_information_user, container, false)
        btnDangXuat = view.findViewById(R.id.btn_dangxuat)
        tv_hoten_infomation = view.findViewById(R.id.tv_hoten_infomation)
        tv_email_infomation = view.findViewById(R.id.tv_email_infomation)
        tv_hoten_infomation.text = responseUser.tenKhachHang
        tv_email_infomation.text = responseUser.email
        btnDangXuat.setOnClickListener {
            val pref = PreferenceManager.getDefaultSharedPreferences(activity!!)
            val edit = pref.edit()
            edit.remove("Token").commit()
            edit.apply()
            loadFragment(ProfileFragment())
        }
        recyclerviewIF = view.findViewById(R.id.recyclerview_infomation_user)
        recyclerviewIF.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        setUpRecyclerview()
        return view
    }
    fun setUpRecyclerview()
    {
        val arrayList = ArrayList<InformationModel>()
        arrayList.add(InformationModel(1,R.drawable.ic_location,"Sổ địa chỉ","Quản lý địa chỉ",R.drawable.ic_next))
        arrayList.add(InformationModel(2,R.drawable.ic_history_buy,"Lịch sử mua hàng","Xem thêm",R.drawable.ic_next))
        arrayList.add(InformationModel(3,R.drawable.ic_favorite_black,"Sản phẩm yêu thích","Xem thêm",R.drawable.ic_next))
        arrayList.add(InformationModel(4,R.drawable.ic_ma_gioi_thieu,"Mã giới thiệu","Xem thêm",R.drawable.ic_next))
        arrayList.add(InformationModel(5,R.drawable.ic_lock_24,"Đổi mật khẩu","Xem thêm",R.drawable.ic_next))
        arrayList.add(InformationModel(6,R.drawable.ic_help,"Hỗ trợ","Xem thêm",R.drawable.ic_next))
        informationAdapter = InformationAdapter(context,arrayList)
        recyclerviewIF.adapter = informationAdapter
    }
    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit()
            return true
        }
        return false
    }
}