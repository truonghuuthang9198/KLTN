package com.example.kltn.screen.profile

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.kltn.R
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.address_handle.CityDialog
import com.example.kltn.screen.retrofit.model.AddAddressModel
import com.example.kltn.screen.retrofit.model.CityModel
import com.example.kltn.screen.retrofit.model.LoginModel
import com.example.kltn.screen.retrofit.reponse.AddAddressResponse
import com.example.kltn.screen.retrofit.reponse.LoginResponse
import com.example.kltn.screen.retrofit.reponse.ManangerAddressResponse
import com.onesignal.OneSignal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

@Suppress("DEPRECATION")
class AddNewAddressFragment() : Fragment(), Parcelable, CityDialog.OnInputSelected {
    lateinit var btnBack_Address: ImageView
    lateinit var edt_tinh_addnew_address: TextView
    lateinit var edt_quan_addnew_address: TextView
    lateinit var edt_phuong_addnew_address: TextView
    lateinit var edt_ho_addnew_address: EditText
    lateinit var edt_ten_addnew_address: EditText
    lateinit var edt_sdt_addnew_address: EditText
    lateinit var edt_diachinha_addnew_address: EditText
    lateinit var ckb_diachigiaohangmacdinh_addnew_address: CheckBox
    lateinit var ckb_diachithanhtoanmacdinh_addnew_address: CheckBox
    lateinit var btn_save_addnew_address: Button
    private var idtitleCity: Int = 0
    private var idtitleDistrict: Int = 0


    override fun sendInput(cityModel: CityModel, type: Int) {
        if (type == 1) {
            this.edt_tinh_addnew_address.text = cityModel.cityname
            this.edt_quan_addnew_address.text = null
            this.edt_phuong_addnew_address.text = null
            idtitleCity = cityModel.id
        } else if (type == 2) {
            this.edt_quan_addnew_address.text = cityModel.cityname
            this.edt_phuong_addnew_address.text = null
            idtitleDistrict = cityModel.id
        } else {
            this.edt_phuong_addnew_address.text = cityModel.cityname
        }
    }

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_addnew_address, container, false)
        btnBack_Address = view.findViewById(R.id.btn_back_addnew_address)
        val fm = activity?.supportFragmentManager

        btnBack_Address.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }

        edt_phuong_addnew_address = view.findViewById(R.id.edt_phuong_addnew_address)
        edt_quan_addnew_address = view.findViewById(R.id.edt_quan_addnew_address)
        edt_tinh_addnew_address = view.findViewById(R.id.edt_tinh_addnew_address)
        edt_ho_addnew_address = view.findViewById(R.id.edt_ho_addnew_address)
        edt_ten_addnew_address = view.findViewById(R.id.edt_ten_addnew_address)
        edt_sdt_addnew_address = view.findViewById(R.id.edt_sdt_addnew_address)
        edt_diachinha_addnew_address = view.findViewById(R.id.edt_diachinha_addnew_address)
        ckb_diachigiaohangmacdinh_addnew_address =
            view.findViewById(R.id.ckb_diachigiaohangmacdinh_addnew_address)
        ckb_diachithanhtoanmacdinh_addnew_address =
            view.findViewById(R.id.ckb_diachithanhtoanmacdinh_addnew_address)
        btn_save_addnew_address = view.findViewById(R.id.btn_save_addnew_address)
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        var makh = pref.getString("MaKH", "")
        Log.d("MaKH",makh.toString())
        var token = pref.getString("TokenLocal", "")
        btn_save_addnew_address.setOnClickListener {
            val rd = Random()
            val soDC = rd.nextInt(101)
            val addAddressModel = AddAddressModel(
                soDC.toString(),
                makh!!,
                edt_diachinha_addnew_address.text.toString(),
                edt_sdt_addnew_address.text.toString(),
                edt_ho_addnew_address.text.toString(),
                edt_ten_addnew_address.text.toString(),
                edt_tinh_addnew_address.text.toString(),
                edt_quan_addnew_address.text.toString(),
                edt_phuong_addnew_address.text.toString(),
                1
            )
            val service =
                RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
            val call = service?.addAddressNew("Bearer " + token, addAddressModel)
            call?.enqueue(object : Callback<AddAddressResponse> {
                override fun onFailure(call: Call<AddAddressResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<AddAddressResponse>,
                    response: Response<AddAddressResponse>
                ) {
                    if (response.isSuccessful) {
                        loadFragment(ManangerAddressFragment())
                        Toast.makeText(context, "Thêm địa chỉ thành công", Toast.LENGTH_LONG)
                            .show()

                    } else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_LONG).show()
                    }
                }
            })
        }


        edt_tinh_addnew_address.setOnClickListener {
            val diaLogCity = CityDialog(1, idtitleCity)
            if (diaLogCity.isAdded) {
                diaLogCity.showsDialog
            } else {
                if (fm != null) {
                    diaLogCity.setTargetFragment(this@AddNewAddressFragment, 1)
                    fm?.let { it1 -> diaLogCity.show(it1, "CityDialog") }
                }
            }

        }

        edt_phuong_addnew_address.setOnClickListener {
            if (edt_quan_addnew_address.text.isEmpty()) {
                Toast.makeText(context, "Vui lòng chọn quận/huyện", Toast.LENGTH_LONG).show()
            } else {
                val diaLogCity = CityDialog(3, idtitleDistrict)
                if (diaLogCity.isAdded) {
                    diaLogCity.showsDialog
                } else {
                    if (fm != null) {
                        diaLogCity.setTargetFragment(this@AddNewAddressFragment, 1)
                        fm?.let { it1 -> diaLogCity.show(it1, "CityDialog") }
                    }
                }
            }

        }

        edt_quan_addnew_address.setOnClickListener {
            if (edt_tinh_addnew_address.text.isEmpty()) {
                Toast.makeText(context, "Vui lòng chọn thành phố", Toast.LENGTH_LONG).show()
            } else {
                val diaLogCity = CityDialog(2, idtitleCity)
                if (diaLogCity.isAdded) {
                    diaLogCity.showsDialog
                } else {
                    if (fm != null) {
                        diaLogCity.setTargetFragment(this@AddNewAddressFragment, 1)
                        fm?.let { it1 -> diaLogCity.show(it1, "CityDialog") }
                    }
                }
            }
        }

        return view
    }


    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment, "ShowMoreDealFragment")
                .addToBackStack(null)
                .commit()
            return true
        }
        return false
    }


    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<AddNewAddressFragment> {
        override fun createFromParcel(parcel: Parcel): AddNewAddressFragment {
            return AddNewAddressFragment(parcel)
        }

        override fun newArray(size: Int): Array<AddNewAddressFragment?> {
            return arrayOfNulls(size)
        }
    }


}