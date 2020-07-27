package com.example.kltn.screen.cart

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.FormatData
import com.example.kltn.screen.cart.adapter.CheckListBillAdapter
import com.example.kltn.screen.cart.model.CartModel
import com.example.kltn.screen.cart.model.CheckBillModel
import com.example.kltn.screen.cart.roomcart.CartViewModel
import com.example.kltn.screen.firebase.ChiTietHDModel
import com.example.kltn.screen.firebase.HoaDonModel
import com.example.kltn.screen.home.SendData
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.custom_dialog_login.*

import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class CheckBillFragment : Fragment() {
    private lateinit var cartViewModel: CartViewModel
    lateinit var recyclerViewCheckBill: RecyclerView
    lateinit var tv_tongtien: TextView
    lateinit var checkListBillAdapter: CheckListBillAdapter
    lateinit var btnBack: ImageView
    lateinit var btnCheckBill: Button
    var sendData: SendData? = null
    val arrayListCheckBill = ArrayList<CheckBillModel>()
    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_information_checkbill, container, false)
        btnBack = view.findViewById(R.id.btn_back_information_checkbill)
        tv_tongtien = view.findViewById(R.id.tv_tongtien_checkbill)
        recyclerViewCheckBill = view.findViewById(R.id.recyclerview_checkbill)
        btnCheckBill = view.findViewById(R.id.btn_checkbill_ok)
        setUpRecyclerView()
        btnCheckBill.setOnClickListener {
            setUpAlertDiaLog()
        }
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
            tongtien += (it.giaTien * it.soLuong)
        }
        tv_tongtien.text = FormatData.formatMoneyVND(tongtien)

        arrayListCart.forEach {
            arrayListCheckBill.add(
                CheckBillModel(
                    it.maSach,
                    it.tenSach,
                    it.soLuong,
                    it.giaTien,
                    it.image
                )
            )
        }

        checkListBillAdapter = CheckListBillAdapter(context, arrayListCheckBill)
        recyclerViewCheckBill.adapter = checkListBillAdapter
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun addBillToFireBase() {
        val arrayList = ArrayList<ChiTietHDModel>()
        var stt: Int = 1
        var tongtienHD: Double = 0.0
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        var makh = pref.getString("MaKH","")
        arrayListCheckBill.forEach {
            arrayList.add(ChiTietHDModel(it.maSach, it.soLuong, it.giaTien))
            tongtienHD += (it.soLuong * it.giaTien)
            stt++
        }
        val database = FirebaseDatabase.getInstance().getReference("Bills")
        val billId = database.push().key
        val hoaDon = HoaDonModel(
            "MaHD",
            makh.toString(),
            java.time.LocalDate.now().toString(),
            tongtienHD,
            arrayList
        )
        database.child(billId!!).setValue(hoaDon).addOnCompleteListener {
            Toast.makeText(context,"Đơn hàng của bạn đã được gửi đi !!!",Toast.LENGTH_LONG).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpAlertDiaLog() {
        val customView =
            LayoutInflater.from(context).inflate(R.layout.custom_dialog_ok, null, false)
        val dialog = AlertDialog.Builder(context!!).setView(customView).create()
        dialog.show()
        dialog.btn_huy_dialog_login.setOnClickListener {
            dialog.dismiss()
        }
        dialog.btn_ok_dialog_login.setOnClickListener {
            addBillToFireBase()
            sendData?.ChangeStateBottomNavigation(3)
            dialog.dismiss()
        }
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            sendData = activity as SendData
        } catch (e: ClassCastException) {
            throw ClassCastException("$activity must implement onSomeEventListener")
        }
    }

}