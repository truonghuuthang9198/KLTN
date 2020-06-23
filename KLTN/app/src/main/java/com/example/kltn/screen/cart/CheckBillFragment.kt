package com.example.kltn.screen.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.cart.adapter.CartAdapter
import com.example.kltn.screen.cart.adapter.CheckListBillAdapter
import com.example.kltn.screen.cart.model.CartModel
import com.example.kltn.screen.cart.model.CheckBillModel
import com.example.kltn.screen.cart.roomdatabase.CartViewModel
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckBillFragment : Fragment() {
    private lateinit var cartViewModel: CartViewModel
    lateinit var recyclerViewCheckBill: RecyclerView
    lateinit var tv_tongtien: TextView
    lateinit var checkListBillAdapter: CheckListBillAdapter

    lateinit var btnBack: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_information_checkbill, container, false)
        btnBack = view.findViewById(R.id.btn_back_information_checkbill)
        tv_tongtien = view.findViewById(R.id.tv_tongtien_checkbill)
        recyclerViewCheckBill = view.findViewById(R.id.recyclerview_checkbill)
        setUpRecyclerView()
        btnBack.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }
        return view
    }
    fun setUpRecyclerView() {
        recyclerViewCheckBill.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        var tongtien: Double = 0.0
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)
        val arrayListCart = cartViewModel.getList() as ArrayList<CartModel>
        arrayListCart.forEach {
            tongtien += (it.giaTien*it.soLuong)
        }
        val localVN = Locale("vi", "VN")
        val numberFormat = NumberFormat.getCurrencyInstance(localVN)
        val tongtienfm = numberFormat.format(tongtien)
        tv_tongtien.text = tongtienfm
        val arrayListCheckBill = ArrayList<CheckBillModel>()
        arrayListCart.forEach {
            arrayListCheckBill.add(CheckBillModel(it.tenSach,it.soLuong,it.giaTien,it.image))
        }
        checkListBillAdapter = CheckListBillAdapter(activity!!,arrayListCheckBill)
        recyclerViewCheckBill.adapter = checkListBillAdapter
    }
}