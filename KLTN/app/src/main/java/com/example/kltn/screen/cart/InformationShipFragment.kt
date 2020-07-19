package com.example.kltn.screen.cart

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.cart.adapter.AddressShipAdapter
import com.example.kltn.screen.cart.model.AddressShipModel
import com.example.kltn.screen.profile.adapter.ManangerAddressAdapter
import com.example.kltn.screen.profile.model.ManangerAddressModel
import com.example.kltn.screen.profile.model.SendArrayAddress
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.reponse.ManagerAddressRespone
import com.google.android.material.textfield.TextInputEditText
import com.google.common.eventbus.Subscribe
import de.greenrobot.event.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
        val arrayList = ArrayList<ManangerAddressModel>()
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        var token = pref.getString("Token", "")
        val service = RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.getListAddress("Bearer " + token)
        call?.enqueue(object : Callback<List<ManagerAddressRespone>> {
            override fun onFailure(call: Call<List<ManagerAddressRespone>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<ManagerAddressRespone>>,
                response: Response<List<ManagerAddressRespone>>
            ) {
                if (response.isSuccessful) {
                    response.body()!!.forEach {
                        arrayList.add(
                            ManangerAddressModel(
                                "Lê Hoàng",
                                "Phúc",
                                it.diaChi,
                                it.soDienThoai,"Địa chỉ khác"
                            )
                        )
                        addressAdapter = AddressShipAdapter(context,arrayList)
                        recyclerviewShip.adapter = addressAdapter
                    }
                }
            }
        })

    }


}

