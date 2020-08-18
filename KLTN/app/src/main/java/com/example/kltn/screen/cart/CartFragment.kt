package com.example.kltn.screen.cart

import android.app.Activity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.FormatData.Companion.formatMoneyVND
import com.example.kltn.screen.cart.adapter.CartAdapter
import com.example.kltn.screen.cart.model.CartModel
import com.example.kltn.screen.cart.roomcart.CartViewModel
import com.example.kltn.screen.home.SendData
import kotlinx.android.synthetic.main.custom_dialog_login.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
@Suppress("DEPRECATION")
class CartFragment : Fragment() {
    lateinit var recyclerviewcart: RecyclerView
    lateinit var thanhtien: TextView
    lateinit var cartAdapter: CartAdapter
    lateinit var btnThanhToan: Button
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

    companion object {
        var arrayListCart: ArrayList<CartModel> = ArrayList<CartModel>()
        var tongtien: Double = 0.0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)
        recyclerviewcart = view!!.findViewById(R.id.recycleview_cart)
        thanhtien = view!!.findViewById(R.id.tv_thanhtien_cart)
        btnThanhToan = view!!.findViewById(R.id.btn_thanhtoan_cart)
        btnThanhToan.setOnClickListener {
            if(cartViewModel.getList().isEmpty())
            {
                Toast.makeText(context,"Vui lòng chọn vài sản phẩm để tiến hành thanh toán",Toast.LENGTH_LONG).show()
            }
            else
            {
                val pref = PreferenceManager.getDefaultSharedPreferences(context)
                var token = pref.getString("TokenLocal", "")
                if (token == "") {
                    setUpBottomSheetDiaLog()
                } else {
                    loadFragment(InformationShipFragment(), "InformationShipFragment")
                }
            }

        }
        setUpRecyclerView()
        return view
    }


    fun setUpRecyclerView() {
        recyclerviewcart.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        arrayListCart = cartViewModel.getList() as ArrayList<CartModel>
        val cartAdapter = CartAdapter(context, arrayListCart)
        recyclerviewcart.adapter = cartAdapter
        cartAdapter.updateThanhTien()
        thanhtien.text = formatMoneyVND(tongtien)
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

    private fun setUpBottomSheetDiaLog() {
        val customView =
            LayoutInflater.from(context).inflate(R.layout.custom_dialog_login, null, false)
        val dialog = AlertDialog.Builder(context!!).setView(customView).create()
        dialog.show()
        dialog.btn_huy_dialog_login.setOnClickListener {
            dialog.dismiss()
        }
        dialog.btn_ok_dialog_login.setOnClickListener {
            sendData?.ChangeStateBottomNavigation(2)
            dialog.dismiss()
        }
    }
}
