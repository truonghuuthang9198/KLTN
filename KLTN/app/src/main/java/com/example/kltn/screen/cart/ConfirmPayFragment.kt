package com.example.kltn.screen.cart

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.cart.adapter.CheckListBillAdapter
import com.example.kltn.screen.cart.model.CheckBillModel
import com.example.kltn.screen.cart.roomcart.CartViewModel
import com.example.kltn.screen.home.SendData

class ConfirmPayFragment : Fragment() {

    lateinit var btn_tieptuc_muahang_confirm_pay: Button
    private lateinit var cartViewModel: CartViewModel
    var sendData: SendData? = null
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            sendData = activity as SendData
        } catch (e: ClassCastException) {
            throw ClassCastException("$activity must implement onSomeEventListener")
        }
    }
    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_confirm_payment, container, false)
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)
        cartViewModel.deleteAll()
        btn_tieptuc_muahang_confirm_pay = view.findViewById(R.id.btn_tieptuc_muahang_confirm_pay)
        btn_tieptuc_muahang_confirm_pay.setOnClickListener {
            sendData?.ChangeStateBottomNavigation(3)
        }
        return view
    }
}