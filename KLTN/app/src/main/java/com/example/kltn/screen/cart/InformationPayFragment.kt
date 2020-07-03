package com.example.kltn.screen.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.cart.adapter.AddressShipAdapter
import com.example.kltn.screen.cart.adapter.MethodPayAdapter
import com.example.kltn.screen.cart.model.AddressShipModel
import com.example.kltn.screen.cart.model.MethodPayModel

class InformationPayFragment : Fragment() {
    lateinit var backButton: ImageView
    lateinit var btnThanhToan: Button
    lateinit var recyclerviewMethodPay: RecyclerView
    lateinit var methodPayAdapter: MethodPayAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_information_pay, container, false)
        backButton = view.findViewById(R.id.btn_back_information_pay)
        btnThanhToan = view.findViewById(R.id.btn_thanhtoan_information_pay)
        recyclerviewMethodPay = view.findViewById(R.id.recyclerview_phuongthucthanhtoan)

        btnThanhToan.setOnClickListener {
            loadFragment(CheckBillFragment(),"CheckBillFragment")
        }
        backButton.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }
        setUpRecyclerview()
        return view
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
    fun setUpRecyclerview()
    {
        recyclerviewMethodPay.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val arrayList = ArrayList<MethodPayModel>()
        arrayList.add(MethodPayModel("Chuyển khoản ngân hàng",-1))
        arrayList.add(MethodPayModel("Thanh toán bằng tiền mặt khi nhận hàng",-1))
        arrayList.add(MethodPayModel("Thẻ ATM nội địa/ Internet Banking",-1))
        arrayList.add(MethodPayModel("Thẻ Visa/ Master/ JCB",-1))
        arrayList.add(MethodPayModel("Ví ZaloPay",-1))
        arrayList.add(MethodPayModel("Ví Momo",-1))
        methodPayAdapter = MethodPayAdapter(activity!!,arrayList)
        recyclerviewMethodPay.adapter = methodPayAdapter
    }
}