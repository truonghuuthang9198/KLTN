package com.example.kltn.screen.profile

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.profile.adapter.ManangerAddressAdapter
import com.example.kltn.screen.profile.model.ManangerAddressModel
import com.example.kltn.screen.event.OnActionData
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.reponse.ManangerAddressResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("DEPRECATION")
class ManangerAddressFragment : Fragment() {
    lateinit var btnBack_Address: ImageView
    lateinit var recyclerViewAddAddress: RecyclerView
    lateinit var btn_add_address: Button
    lateinit var manangerAddressAdapter: ManangerAddressAdapter
    private var onActionData: OnActionData<ManangerAddressModel>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mananger_address, container, false)
        recyclerViewAddAddress = view.findViewById(R.id.recyclerview_address)
        btnBack_Address = view.findViewById(R.id.btn_back_add_address)
        btn_add_address = view.findViewById(R.id.btn_addnew_address)
        recyclerViewAddAddress.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        btnBack_Address = view.findViewById(R.id.btn_back_add_address)
        btnBack_Address.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }
        btn_add_address.setOnClickListener {
            loadFragment(AddNewAddressFragment(), "AddNewAddressFragment")
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

    fun setUpRecyclerview() {
        val arrayList = ArrayList<ManangerAddressModel>()
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        var token = pref.getString("Token", "")
        val service = RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.getListAddress("Bearer " + token)
        call?.enqueue(object : Callback<List<ManangerAddressResponse>> {
            override fun onFailure(call: Call<List<ManangerAddressResponse>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<ManangerAddressResponse>>,
                response: Response<List<ManangerAddressResponse>>
            ) {
                if (response.isSuccessful) {
                    response.body()!!.forEachIndexed { index, managerAddressRespone ->
                        arrayList.add(
                            ManangerAddressModel(
                                index,
                                managerAddressRespone.maSo,
                                managerAddressRespone.maKhachHang,
                                managerAddressRespone.ho,
                                managerAddressRespone.ten,
                                managerAddressRespone.soDienThoai,
                                managerAddressRespone.thanhPho,
                                managerAddressRespone.quan,
                                managerAddressRespone.phuong,
                                managerAddressRespone.diaChi,
                                managerAddressRespone.loaiDiaChi
                            )
                        )
                    }
                    manangerAddressAdapter =
                        ManangerAddressAdapter(context, arrayList, onActionData!!)
                    recyclerViewAddAddress.adapter = manangerAddressAdapter
                }
            }
        })
        onActionData = object : OnActionData<ManangerAddressModel> {
            override fun onAction(data: ManangerAddressModel) {
                loadFragment(UpdateAddressFragment(data), "UpdateAddressFragment")
            }
        }

    }
}