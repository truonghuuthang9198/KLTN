package com.example.kltn.screen.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.cart.adapter.AddressShipAdapter
import com.example.kltn.screen.cart.model.AddressShipModel
import com.example.kltn.screen.profile.model.SendArrayAddress
import com.google.android.material.textfield.TextInputEditText
import com.google.common.eventbus.Subscribe
import de.greenrobot.event.EventBus


class InformationShipFragment : Fragment() {

    lateinit var btnBackShip: ImageView
    lateinit var btnGiaoHang: Button
    lateinit var recyclerviewShip: RecyclerView
    lateinit var addressAdapter: AddressShipAdapter
    lateinit var tv_ghichukhachhang: TextInputEditText
    lateinit var chk_box_ghichu: CheckBox
//    override fun onResume() {
//        super.onResume()
//        EventBus.getDefault().register(this)
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_information_ship, container, false)
        btnBackShip = view.findViewById(R.id.btn_back_information_ship)
        btnGiaoHang = view.findViewById(R.id.btn_giaohang_information_ship)
        recyclerviewShip = view.findViewById(R.id.reyclerview_address_ship)
        tv_ghichukhachhang = view.findViewById(R.id.tv_ghichukhachhang)
        chk_box_ghichu = view.findViewById(R.id.chk_box_ghichu)
        tv_ghichukhachhang.visibility = View.GONE
        var checked = 0
        chk_box_ghichu.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            if (checked ==0) {
                tv_ghichukhachhang.visibility = View.VISIBLE
                checked = 1
            }
            else {
                tv_ghichukhachhang.visibility = View.GONE
                checked = 0
            }

        })

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

//        arrayList.add(AddressShipModel("Trương Hữu Thắng - 0384180187","76/32/13 Lê Trọng Tấn, Phường Tây Thạnh,Quận Tân Phú, TP Hồ Chí Minh",-1))
//        arrayList.add(AddressShipModel("Trương Hữu Thắng - 0384180187","76/32/13 Lê Trọng Tấn, Phường Tây Thạnh,Quận Tân Phú, TP Hồ Chí Minh",-1))
        addressAdapter = AddressShipAdapter(context,arrayList)
        recyclerviewShip.adapter = addressAdapter
    }


}

