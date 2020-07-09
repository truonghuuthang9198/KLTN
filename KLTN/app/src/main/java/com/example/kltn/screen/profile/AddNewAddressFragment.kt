package com.example.kltn.screen.profile

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kltn.R
import com.example.kltn.screen.retrofit.address_handle.CityDialog
import com.example.kltn.screen.retrofit.model.CityModel

class AddNewAddressFragment() : Fragment(), Parcelable, CityDialog.OnInputSelected {
    lateinit var btnBack_Address: ImageView
    lateinit var edt_tinh_addnew_address: TextView
    lateinit var edt_quan_addnew_address: TextView
    lateinit var edt_phuong_addnew_address: TextView
    private var idtitleCity: Int = 0
    private var idtitleDistrict: Int = 0



    override fun sendInput(cityModel: CityModel,type: Int) {
        if(type==1) {
            this.edt_tinh_addnew_address.text = cityModel.cityname
            this.edt_quan_addnew_address.text =null
            this.edt_phuong_addnew_address.text =null
            idtitleCity = cityModel.id
        }
        else if(type==2)
        {
            this.edt_quan_addnew_address.text = cityModel.cityname
            this.edt_phuong_addnew_address.text =null
            idtitleDistrict = cityModel.id
        }
        else
        {
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

//        val bundle = this.arguments
//        if (bundle != null) {
//            val cityModel = bundle.getParcelable<CityModel>("cityModel")!!
//            edt_tinh_addnew_address.text = cityModel.cityname
//        }


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
            if(edt_quan_addnew_address.text.isEmpty())
            {
                Toast.makeText(context,"Vui lòng chọn quận/huyện",Toast.LENGTH_LONG).show()
            }
            else {
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
            if(edt_tinh_addnew_address.text.isEmpty())
            {
                Toast.makeText(context,"Vui lòng chọn thành phố",Toast.LENGTH_LONG).show()
            }
            else {
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