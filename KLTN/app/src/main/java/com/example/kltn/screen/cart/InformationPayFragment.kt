package com.example.kltn.screen.cart

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.FormatData
import com.example.kltn.screen.cart.adapter.MethodPayAdapter
import com.example.kltn.screen.cart.model.CartModel
import com.example.kltn.screen.cart.model.MethodPayModel
import com.example.kltn.screen.cart.roomcart.CartViewModel
import com.example.kltn.screen.event.OnActionData
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class InformationPayFragment(val diachi:String,val tinh:String) : Fragment() {
    lateinit var backButton: ImageView
    lateinit var btnThanhToan: Button
    lateinit var recyclerviewMethodPay: RecyclerView
    lateinit var methodPayAdapter: MethodPayAdapter
    lateinit var tv_handle_thanhtien_information_pay: TextView
    lateinit var tv_handle_phivanchuyen_information_pay:TextView
    lateinit var tv_handle_tongtien_information_pay:TextView
    lateinit var tvShipCost:TextView
    lateinit var tvDateShip:TextView
    lateinit var rdb_giaohang:RadioButton
    private var onActionData: OnActionData<MethodPayModel>? = null
    private lateinit var cartViewModel: CartViewModel
    private var PTTT: Int = 0
    private var tongtien: Double = 0.0
    private var phiship:Double = 0.0
    private var ngaygiao:Int = 0
    private var tongtienFinal:Double = 0.0
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_information_pay, container, false)
        backButton = view.findViewById(R.id.btn_back_information_pay)
        btnThanhToan = view.findViewById(R.id.btn_thanhtoan_information_pay)
        tvShipCost = view.findViewById(R.id.tvShipCost)
        tvDateShip = view.findViewById(R.id.tvDataShip)
        rdb_giaohang = view.findViewById(R.id.rdb_giaohang)
        tv_handle_thanhtien_information_pay= view.findViewById(R.id.tv_handle_thanhtien_information_pay)
        tv_handle_phivanchuyen_information_pay= view.findViewById(R.id.tv_handle_phivanchuyen_information_pay)
        tv_handle_tongtien_information_pay= view.findViewById(R.id.tv_handle_tongtien_information_pay)
        recyclerviewMethodPay = view.findViewById(R.id.recyclerview_phuongthucthanhtoan)
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)
        val arrayListCart = cartViewModel.getList() as ArrayList<CartModel>
        arrayListCart.forEach {
            tongtien += (it.giaTien * it.soLuong)
        }
        if(tinh.compareTo("TP Hồ Chí Minh") == 0 || tinh.compareTo("Hà Nội")==0)
        {
            if(tongtien<140000.00)
            {
                phiship = 15000.00
                ngaygiao = 1
            }
            else
            {
                phiship = 0.0
                ngaygiao = 1
            }
        }
        else
        {
            if(tongtien<250000)
            {
                phiship = 25000.00
                ngaygiao = 3
            }
            else
            {
                phiship = 0.0
                ngaygiao = 3
            }
        }
        tongtienFinal = tongtien - phiship
        tvShipCost.append(FormatData.formatMoneyVND(phiship))
        tvDateShip.append(FormatData.convertDateFormat(java.time.LocalDate.now().plusDays(ngaygiao.toLong()).toString(),
            SimpleDateFormat("yyyy-MM-dd"),
            SimpleDateFormat("dd/MM/yyyy")
        ))
        btnThanhToan.setOnClickListener {
            loadFragment(CheckBillFragment(PTTT,diachi,tongtienFinal))
        }
        backButton.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }
        tv_handle_thanhtien_information_pay.text= FormatData.formatMoneyVND(tongtien)
        tv_handle_phivanchuyen_information_pay.text = FormatData.formatMoneyVND(phiship)
        tv_handle_tongtien_information_pay.text = FormatData.formatMoneyVND(tongtienFinal)
        setUpRecyclerview()

        return view
    }
    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            (context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .addToBackStack(null)
                .commit()
            return true
        }
        return false
    }
    fun setUpRecyclerview()
    {
        recyclerviewMethodPay.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val arrayList = ArrayList<MethodPayModel>()
        arrayList.add(MethodPayModel(0,"Thanh toán bằng tiền mặt khi nhận hàng"))
        arrayList.add(MethodPayModel(1,"Chuyển khoản ngân hàng"))
        arrayList.add(MethodPayModel(2,"Thanh toán bằng PayPal"))
        arrayList[0].selectedState = true
        onActionData = object : OnActionData<MethodPayModel> {
            override fun onAction(data: MethodPayModel) {
                arrayList.forEach {
                    if(it.id == data.id) {
                        it.selectedState = true
                        PTTT = it.id
                    }
                }
            }
        }
        methodPayAdapter = MethodPayAdapter(context,arrayList,onActionData!!)
        recyclerviewMethodPay.adapter = methodPayAdapter
    }
}