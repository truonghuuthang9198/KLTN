package com.example.kltn.screen.cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.cart.adapter.CartAdapter
import com.example.kltn.screen.cart.model.CartModel
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

    companion object {
        var arrayListCart: ArrayList<CartModel> = ArrayList<CartModel>()
        var tongtien: Double = 0.0

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        recyclerviewcart = view!!.findViewById(R.id.recycleview_cart)
        recyclerviewcart.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        thanhtien = view!!.findViewById(R.id.tv_thanhtien_cart)
//        arrayListCart = ArrayList<CartModel>()
        addList()
        setUpRecyclerView()
        return view
    }

    fun addList() {
        var arrayListCart1: ArrayList<CartModel> = ArrayList<CartModel>()
        if (arrayListCart.isEmpty()) {
            arrayListCart1.add(
                CartModel(
                    "Đắc nhân tâm 1",
                    1,
                    26000.00,
                    R.drawable.vd_sach
                )
            )
            arrayListCart1.add(
                CartModel(
                    "Đắc nhân tâm 2",
                    2,
                    28000.00,
                    R.drawable.vd_sach
                )
            )
            arrayListCart1.add(
                CartModel(
                    "Đắc nhân tâm 3",
                    4,
                    35000.00,
                    R.drawable.vd_sach
                )
            )
            arrayListCart = arrayListCart1
        }

    }
    fun setUpRecyclerView() {
        cartAdapter = CartAdapter(activity!!,arrayListCart)
        cartAdapter.updateThanhTien()
        recyclerviewcart.adapter = cartAdapter
        val localVN = Locale("vi", "VN")
        val numberFormat = NumberFormat.getCurrencyInstance(localVN)
        val thanhtienfm = numberFormat.format(tongtien)
        //thành tiền
        thanhtien.text = thanhtienfm
    }

}
