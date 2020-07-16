package com.example.kltn.screen.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.profile.adapter.ManangerAddressAdapter
import com.example.kltn.screen.profile.model.ManangerAddressModel
import com.example.kltn.screen.event.OnActionData
import com.example.kltn.screen.profile.model.SendArrayAddress
import de.greenrobot.event.EventBus


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
        recyclerViewAddAddress.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        btnBack_Address = view.findViewById(R.id.btn_back_add_address)
        btnBack_Address.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }
        btn_add_address.setOnClickListener {
            loadFragment(AddNewAddressFragment(),"AddNewAddressFragment")
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
        val arrayList = ArrayList<ManangerAddressModel>()
        arrayList.add(ManangerAddressModel("Trương Hữu","Thắng","73/32/13 Lê Trọng Tấn, Phường Tây Thạnh, Quận Tân Phú, TP HCM","0384180187","Địa chỉ thanh toán mặc định"))
        arrayList.add(ManangerAddressModel("Lê Thanh","Tuyên","115/22 Lê Trọng Tấn, Phường Sơn Kỳ, Quận Tân Phú, TP HCM","0384180187","Địa chỉ giao hàng mặc định"))
        arrayList.add(ManangerAddressModel("Lê Hoàng","Phúc","115/66 Nguyễn Đỗ Cung, Phường Tây Thạnh, Quận Tân Phú, TP HCM","0384180667","Địa chỉ khác"))
        onActionData = object : OnActionData<ManangerAddressModel> {
            override fun onAction(data: ManangerAddressModel) {
                loadFragment(UpdateAddressFragment(),"UpdateAddressFragment")
            }
        }
        manangerAddressAdapter = ManangerAddressAdapter(context,arrayList,onActionData!!)
        recyclerViewAddAddress.adapter = manangerAddressAdapter
    }
}