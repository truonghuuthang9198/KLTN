package com.example.kltn.screen.profile
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.profile.adapter.FavoriteAdapter
import com.example.kltn.screen.profile.adapter.HistoryBillAdapter
import com.example.kltn.screen.profile.model.FavoriteModel
import com.example.kltn.screen.profile.model.HistoryBillModel
import kotlinx.android.synthetic.main.fragment_information_ship.*

class HistoryFragment : Fragment() {
    lateinit var recyclerview_bill: RecyclerView
    lateinit var adapterHistoryBill: HistoryBillAdapter
    lateinit var btn_back_history_muahang: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history_sale_book, container, false)
        recyclerview_bill = view.findViewById(R.id.recyclerview_history_bill)
        recyclerview_bill.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        btn_back_history_muahang = view.findViewById(R.id.btn_back_history_muahang)
        btn_back_history_muahang.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }
        setUpRecyclerview()
        return view
    }
    fun setUpRecyclerview()
    {
        val arrayList = ArrayList<HistoryBillModel>()
        arrayList.add(HistoryBillModel("HD0001","10/02/2020",200000.00))
        arrayList.add(HistoryBillModel("HD0002","09/01/1998",500000.00))
        arrayList.add(HistoryBillModel("HD0003","12/02/1998",300000.00))
        adapterHistoryBill = HistoryBillAdapter(context,arrayList)
        recyclerview_bill.adapter = adapterHistoryBill
    }
}