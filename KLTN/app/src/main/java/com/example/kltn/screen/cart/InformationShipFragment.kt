package com.example.kltn.screen.cart

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.cart.adapter.AddressShipAdapter
import com.example.kltn.screen.cart.model.AddressShipModel
import com.example.kltn.screen.event.OnActionData
import com.example.kltn.screen.home.model.FilterModel
import com.example.kltn.screen.profile.AddNewAddressFragment
import com.example.kltn.screen.profile.adapter.ManangerAddressAdapter
import com.example.kltn.screen.profile.model.ManangerAddressModel
import com.example.kltn.screen.profile.model.SendArrayAddress
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.reponse.ManangerAddressResponse
import com.google.android.material.textfield.TextInputEditText
import com.google.common.eventbus.Subscribe
import de.greenrobot.event.EventBus
import kotlinx.android.synthetic.main.custom_dialog_login.*
import kotlinx.android.synthetic.main.custom_dialog_login.btn_huy_dialog_login
import kotlinx.android.synthetic.main.custom_dialog_login.btn_ok_dialog_login
import kotlinx.android.synthetic.main.dialog_check_addressship.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InformationShipFragment : Fragment() {

    lateinit var btnBackShip: ImageView
    lateinit var btnGiaoHang: Button
    lateinit var recyclerviewShip: RecyclerView
    lateinit var addressAdapter: AddressShipAdapter
    lateinit var tv_ghichukhachhang: TextInputEditText
    lateinit var btn_add_address_other: LinearLayout
    lateinit var chk_box_ghichu: CheckBox
    private var onActionData: OnActionData<ManangerAddressModel>? = null
    lateinit var arrayList: ArrayList<ManangerAddressModel>
    private var diachi:String =""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_information_ship, container, false)
        btnBackShip = view.findViewById(R.id.btn_back_information_ship)
        btnGiaoHang = view.findViewById(R.id.btn_giaohang_information_ship)
        recyclerviewShip = view.findViewById(R.id.reyclerview_address_ship)
        btn_add_address_other = view.findViewById(R.id.btn_add_address_other)
        tv_ghichukhachhang = view.findViewById(R.id.tv_ghichukhachhang)
        chk_box_ghichu = view.findViewById(R.id.chk_box_ghichu)
        tv_ghichukhachhang.visibility = View.GONE
        var checked = 0
        chk_box_ghichu.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            if (checked == 0) {
                tv_ghichukhachhang.visibility = View.VISIBLE
                checked = 1
            } else {
                tv_ghichukhachhang.visibility = View.GONE
                checked = 0
            }

        })
        btn_add_address_other.setOnClickListener {
            loadFragment(AddNewAddressFragment(), "AddNewAddressFragment")
        }

        btnBackShip.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }
        btnGiaoHang.setOnClickListener {
            setUpDialogCheck()
        }
        //setUpTestRecyclerview()
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
        arrayList = ArrayList<ManangerAddressModel>()
        recyclerviewShip.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        var token = pref.getString("TokenLocal", "")
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
                    response.body()!!.forEachIndexed { index, manangerAddressResponse ->
                        arrayList.add(
                            ManangerAddressModel(
                                index,
                                manangerAddressResponse.maSo,
                                manangerAddressResponse.maKhachHang,
                                manangerAddressResponse.ho,
                                manangerAddressResponse.ten,
                                manangerAddressResponse.soDienThoai,
                                manangerAddressResponse.thanhPho,
                                manangerAddressResponse.quan,
                                manangerAddressResponse.phuong,
                                manangerAddressResponse.diaChi,
                                manangerAddressResponse.loaiDiaChi
                            )
                        )
                    }
                    arrayList[0].chose = true
                    if(diachi=="")
                    {
                        diachi =  arrayList.first().address + ", " + arrayList.first().xa + ", " + arrayList.first().quan + ", " + arrayList.first().tinh + ", Việt Nam"
                    }

                    onActionData = object : OnActionData<ManangerAddressModel> {
                        override fun onAction(data: ManangerAddressModel) {
                            arrayList.forEach {
                                if(it.id == data.id) {
                                    it.chose = true
                                    diachi =  it.address + ", " + it.xa + ", " + it.quan + ", " + it.tinh + ", Việt Nam"
                                }
                                else
                                {
                                    it.chose = false
                                }
                            }

                        }
                    }
                    addressAdapter = AddressShipAdapter(context, arrayList, onActionData!!)
                    recyclerviewShip.adapter = addressAdapter
                }
            }
        })

    }


    private fun setUpDialogCheck() {
        val customView =
            LayoutInflater.from(context).inflate(R.layout.dialog_check_addressship, null, false)
        val dialog = AlertDialog.Builder(context!!).setView(customView).create()
        var isChosse: Boolean = false
        arrayList.forEach {
            if (it.chose) {
                isChosse = true
                dialog.show()
                dialog.tv_addressship.text =
                    it.address + ", " + it.xa + ", " + it.quan + ", " + it.tinh + ", Việt Nam"
                dialog.btn_huy_dialog_login.setOnClickListener {
                    dialog.dismiss()
                }
                var tinh = it.tinh
                dialog.btn_ok_dialog_login.setOnClickListener {
                    loadFragment(InformationPayFragment(diachi,tinh), "InformationPayFragment")
                    dialog.dismiss()
                }
            }
        }
        if (!isChosse) {
            Toast.makeText(activity, "Vui lòng chọn địa chỉ thanh toán", Toast.LENGTH_LONG).show()
        }
    }
}


