package com.example.kltn.screen.profile

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kltn.R
import com.example.kltn.screen.profile.adapter.ManangerAddressAdapter
import com.example.kltn.screen.profile.model.ManangerAddressModel
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.model.AddAddressModel
import com.example.kltn.screen.retrofit.reponse.ManangerAddressResponse
import com.example.kltn.screen.retrofit.reponse.UpdateAddressResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("DEPRECATION")
class UpdateAddressFragment(var data: ManangerAddressModel) : Fragment() {
    lateinit var btnBack_Address: ImageView
    lateinit var edt_ho_update_address: EditText
    lateinit var edt_ten_update_address: EditText
    lateinit var edt_sdt_update_address: EditText
    lateinit var edt_tinh_address: EditText
    lateinit var edt_quan_update_address: EditText
    lateinit var edt_xaphuong_address: EditText
    lateinit var edt_diachinha_update_address: EditText
    lateinit var btn_save_update_address: Button
    lateinit var btn_delete_update_address: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update_address, container, false)
        btnBack_Address = view.findViewById(R.id.btn_back_address)
        edt_ho_update_address = view.findViewById(R.id.edt_ho_update_address)
        edt_ten_update_address = view.findViewById(R.id.edt_ten_update_address)
        edt_sdt_update_address = view.findViewById(R.id.edt_sdt_update_address)
        edt_tinh_address = view.findViewById(R.id.edt_tinh_address)
        edt_quan_update_address = view.findViewById(R.id.edt_quan_update_address)
        edt_xaphuong_address = view.findViewById(R.id.edt_xaphuong_address)
        edt_diachinha_update_address = view.findViewById(R.id.edt_diachinha_update_address)
        btn_save_update_address = view.findViewById(R.id.btn_save_update_address)
        btn_save_update_address.setOnClickListener {
            val addAddressModel = AddAddressModel(
                data.maSo,
                data.maKH,
                edt_diachinha_update_address.text.toString(),
                edt_sdt_update_address.text.toString(),
                edt_ho_update_address.text.toString(),
                edt_ten_update_address.text.toString(),
                edt_tinh_address.text.toString(),
                edt_quan_update_address.text.toString(),
                edt_xaphuong_address.text.toString(),
                1
            )
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            var token = pref.getString("Token", "")
            val service =
                RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
            val call = service?.updateAddress("Bearer " + token, addAddressModel)
            call?.enqueue(object : Callback<UpdateAddressResponse> {
                override fun onFailure(call: Call<UpdateAddressResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<UpdateAddressResponse>,
                    response: Response<UpdateAddressResponse>
                ) {
                    Toast.makeText(context, response.body()!!.message, Toast.LENGTH_LONG).show()
                    if (fragmentManager!!.backStackEntryCount > 0) {
                        fragmentManager!!.popBackStack()
                    }
                }
            })
        }
        btn_delete_update_address = view.findViewById(R.id.btn_delete_update_address)
        btnBack_Address.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }
        edt_ho_update_address.setText(data.ho)
        edt_ten_update_address.setText(data.ten)
        edt_sdt_update_address.setText(data.sdt)
        edt_tinh_address.setText(data.tinh)
        edt_quan_update_address.setText(data.quan)
        edt_xaphuong_address.setText(data.xa)
        edt_diachinha_update_address.setText(data.address)
        btn_delete_update_address.setOnClickListener {

        }
        return view
    }
}