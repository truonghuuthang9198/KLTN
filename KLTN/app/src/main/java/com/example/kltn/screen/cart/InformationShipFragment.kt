package com.example.kltn.screen.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.cart.adapter.AddressShipAdapter
import com.example.kltn.screen.cart.adapter.CartAdapter
import com.example.kltn.screen.cart.model.AddressShipModel
import com.example.kltn.screen.cart.model.CartModel
import com.example.kltn.screen.cart.roomdatabase.CartViewModel
import com.example.kltn.screen.suggest.adapter.SuggestAdapter
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class InformationShipFragment : Fragment() {

    lateinit var btnBackShip: ImageView
    lateinit var btnGiaoHang: Button
    lateinit var recyclerviewShip: RecyclerView
    lateinit var addressAdapter: AddressShipAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_information_ship, container, false)
        btnBackShip = view.findViewById(R.id.btn_back_information_ship)
        btnGiaoHang = view.findViewById(R.id.btn_giaohang_information_ship)
        recyclerviewShip = view.findViewById(R.id.reyclerview_address_ship)
        btnBackShip.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }
        btnGiaoHang.setOnClickListener {
            loadFragment(InformationPayFragment(),"InformationPayFragment")
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
        recyclerviewShip.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val arrayList = ArrayList<AddressShipModel>()
        arrayList.add(AddressShipModel("Trương Hữu Thắng - 0384180187","76/32/13 Lê Trọng Tấn, Phường Tây Thạnh,Quận Tân Phú, TP Hồ Chí Minh",-1))
        arrayList.add(AddressShipModel("Trương Hữu Thắng - 0384180187","76/32/13 Lê Trọng Tấn, Phường Tây Thạnh,Quận Tân Phú, TP Hồ Chí Minh",-1))
        addressAdapter = AddressShipAdapter(context,arrayList)
        recyclerviewShip.adapter = addressAdapter
    }
}