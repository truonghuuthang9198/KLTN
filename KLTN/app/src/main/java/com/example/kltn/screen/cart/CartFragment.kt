package com.example.kltn.screen.cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.cart.adapter.CartAdapter
import com.example.kltn.screen.cart.model.CartModel
import com.example.kltn.screen.cart.roomdatabase.CartViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class CartFragment : Fragment() {
    lateinit var recyclerviewcart: RecyclerView
    lateinit var thanhtien: TextView
    lateinit var cartAdapter: CartAdapter
    lateinit var btnThanhToan: Button
    private lateinit var cartViewModel: CartViewModel

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
        recyclerviewcart = view!!.findViewById(R.id.recycleview_cart)
        thanhtien = view!!.findViewById(R.id.tv_thanhtien_cart)
        btnThanhToan = view!!.findViewById(R.id.btn_thanhtoan_cart)
        btnThanhToan.setOnClickListener {
            loadFragment(InformationShipFragment(),"InformationShipFragment")
        }
        setUpRecyclerView()
        return view
    }


    fun setUpRecyclerView() {
        recyclerviewcart.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        cartViewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)
        arrayListCart = cartViewModel.getList() as ArrayList<CartModel>
        val cartAdapter = CartAdapter(activity!!, arrayListCart)
        recyclerviewcart.adapter = cartAdapter
        cartAdapter.updateThanhTien()
        val localVN = Locale("vi", "VN")
        val numberFormat = NumberFormat.getCurrencyInstance(localVN)
        val thanhtienfm = numberFormat.format(tongtien)
        thanhtien.text = thanhtienfm
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
}
