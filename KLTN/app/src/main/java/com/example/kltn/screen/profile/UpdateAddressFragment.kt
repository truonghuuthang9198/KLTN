package com.example.kltn.screen.profile

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.kltn.R
import com.example.kltn.screen.profile.model.ManangerAddressModel
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.address_handle.CityDialog
import com.example.kltn.screen.retrofit.request.AddAddressRequest
import com.example.kltn.screen.retrofit.address_handle.CityModel
import com.example.kltn.screen.retrofit.reponse.DeleteAddressResponse
import com.example.kltn.screen.retrofit.reponse.UpdateAddressResponse
import com.example.kltn.screen.retrofit.request.UpdateAddressRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("DEPRECATION")
class UpdateAddressFragment(var data: ManangerAddressModel) : Fragment(),CityDialog.OnInputSelected {
    lateinit var btnBack_Address: ImageView
    lateinit var edt_ho_update_address: EditText
    lateinit var edt_ten_update_address: EditText
    lateinit var edt_sdt_update_address: EditText
    lateinit var edt_tinh_address: TextView
    lateinit var edt_quan_update_address: TextView
    lateinit var edt_xaphuong_address: TextView
    lateinit var edt_diachinha_update_address: EditText
    lateinit var btn_save_update_address: Button
    lateinit var btn_delete_update_address: Button
    lateinit var ckb_diachigiaohangmacdinh_update_address: CheckBox
    lateinit var ckb_diachithanhtoanmacdinh_update_address:CheckBox
    private var idtitleCity: Int = 0
    private var idtitleDistrict: Int = 0

    override fun sendInput(cityModel: CityModel, type: Int) {
        if (type == 1) {
            this.edt_tinh_address.text = cityModel.cityname
            this.edt_quan_update_address.text = null
            this.edt_xaphuong_address.text = null
            idtitleCity = cityModel.id
        } else if (type == 2) {
            this.edt_quan_update_address.text = cityModel.cityname
            this.edt_xaphuong_address.text = null
            idtitleDistrict = cityModel.id
        } else {
            this.edt_xaphuong_address.text = cityModel.cityname
        }
    }
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
        ckb_diachigiaohangmacdinh_update_address = view.findViewById(R.id.ckb_diachigiaohangmacdinh_update_address)
        ckb_diachithanhtoanmacdinh_update_address = view.findViewById(R.id.ckb_diachithanhtoanmacdinh_update_address)

        val fm = activity?.supportFragmentManager
        if (data.checkaddress == 1)
        {
            ckb_diachigiaohangmacdinh_update_address.isChecked = true
        }
        else if(data.checkaddress == 2)
        {
            ckb_diachithanhtoanmacdinh_update_address.isChecked = true
        }
        else
        {
            ckb_diachigiaohangmacdinh_update_address.isChecked = true
            ckb_diachithanhtoanmacdinh_update_address.isChecked = true
        }


        btn_save_update_address.setOnClickListener {
            var loaiDiaChi = 1
            if(ckb_diachigiaohangmacdinh_update_address.isChecked == true && ckb_diachithanhtoanmacdinh_update_address.isChecked == false)
            {
                loaiDiaChi = 1
            }
            else if(ckb_diachithanhtoanmacdinh_update_address.isChecked == true && ckb_diachigiaohangmacdinh_update_address.isChecked == false)
            {
                loaiDiaChi = 2
            }
            else
            {
                loaiDiaChi = 3
            }
            val updateAddressRequest = UpdateAddressRequest(
                data.maSo,
                data.maKH,
                edt_diachinha_update_address.text.toString(),
                edt_sdt_update_address.text.toString(),
                edt_ho_update_address.text.toString(),
                edt_ten_update_address.text.toString(),
                edt_tinh_address.text.toString(),
                edt_quan_update_address.text.toString(),
                edt_xaphuong_address.text.toString(),
                loaiDiaChi
            )
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            var token = pref.getString("TokenLocal", "")
            val service =
                RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
            val call = service?.updateAddress("Bearer " + token, updateAddressRequest)
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
        btn_delete_update_address.setOnClickListener {
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            var token = pref.getString("TokenLocal", "")
            val service =
                RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
            val call = service?.deleteAddress("Bearer " + token, data.maSo)
            call?.enqueue(object : Callback<DeleteAddressResponse> {
                override fun onFailure(call: Call<DeleteAddressResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<DeleteAddressResponse>,
                    response: Response<DeleteAddressResponse>
                ) {
                    if(response.body()!!.statusCode == 200) {
                        Toast.makeText(context, "Xoá thành công", Toast.LENGTH_LONG).show()
                        if (fragmentManager!!.backStackEntryCount > 0) {
                            fragmentManager!!.popBackStack()
                        }
                    }
                    else
                    {
                        Toast.makeText(context,"Xoá thất bại",Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
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
        edt_tinh_address.setOnClickListener {
            val diaLogCity = CityDialog(1, idtitleCity)
            if (diaLogCity.isAdded) {
                diaLogCity.showsDialog
            } else {
                if (fm != null) {
                    diaLogCity.setTargetFragment(this@UpdateAddressFragment, 1)
                    fm?.let { it1 -> diaLogCity.show(it1, "CityDialog") }
                }
            }

        }
        edt_quan_update_address.setOnClickListener {
            if (edt_tinh_address.text.isEmpty()) {
                Toast.makeText(context, "Vui lòng chọn thành phố", Toast.LENGTH_LONG).show()
            } else {
                val diaLogCity = CityDialog(2, idtitleCity)
                if (diaLogCity.isAdded) {
                    diaLogCity.showsDialog
                } else {
                    if (fm != null) {
                        diaLogCity.setTargetFragment(this@UpdateAddressFragment, 1)
                        fm?.let { it1 -> diaLogCity.show(it1, "CityDialog") }
                    }
                }
            }
        }
        edt_xaphuong_address.setOnClickListener {
            if (edt_quan_update_address.text.isEmpty()) {
                Toast.makeText(context, "Vui lòng chọn quận/huyện", Toast.LENGTH_LONG).show()
            } else {
                val diaLogCity = CityDialog(3, idtitleDistrict)
                if (diaLogCity.isAdded) {
                    diaLogCity.showsDialog
                } else {
                    if (fm != null) {
                        diaLogCity.setTargetFragment(this@UpdateAddressFragment, 1)
                        fm?.let { it1 -> diaLogCity.show(it1, "CityDialog") }
                    }
                }
            }
        }

        return view
    }
}