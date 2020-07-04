package com.example.kltn.screen.home.deals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.kltn.R
import com.example.kltn.screen.home.adapter.DealAdapter
import com.example.kltn.screen.home.adapter.ShowMoreDealAdapter
import com.example.kltn.screen.home.model.DealModel
import com.example.kltn.screen.home.model.ShowMoreDealModel
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.model.CityModel
import com.example.kltn.screen.retrofit.reponse.CityReponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChildShowMoreDealFragment(val tabId:Int) : Fragment(){
    lateinit var recyclerViewSMDeal: RecyclerView
    lateinit var showMoreDealAdapter: ShowMoreDealAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_child_show_more_deal, container, false)
        recyclerViewSMDeal = view.findViewById(R.id.recyclerview_show_more_deal)
        setUpRecyclerView()
        return view
    }
    fun setUpRecyclerView()
    {
        recyclerViewSMDeal.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.HORIZONTAL,false)
        recyclerViewSMDeal.layoutManager = GridLayoutManager(activity,2)
        val arrayList = ArrayList<DealModel>()
        val listTab0 = ArrayList<DealModel>()

        arrayList.forEach{
            if(it.tabId == tabId)
            {
                listTab0.add(it)
            }
        }
        showMoreDealAdapter = ShowMoreDealAdapter(listTab0)
        recyclerViewSMDeal.adapter = showMoreDealAdapter
    }

//    override fun onCallBack() {
//    }

//    override fun onSetBackRecyclerView() {
//        recyclerViewSMDeal = view!!.findViewById(R.id.recyclerview_show_more_deal)
//        recyclerViewSMDeal.layoutManager = LinearLayoutManager(activity,
//            LinearLayoutManager.HORIZONTAL,false)
//        recyclerViewSMDeal.layoutManager = GridLayoutManager(activity,2)
//        val arrayListVD= ArrayList<ShowMoreDealModel>()
//        arrayListVD.add(
//            ShowMoreDealModel(0,0,R.drawable.vd1_sach,"Dạy Trẻ Biết Đọc Sớm",53720.00,79000.00)
//        )
//        arrayListVD.add(
//            ShowMoreDealModel(1,0,R.drawable.vd1_sach,"Get Set Go: Mathematics Equals",36000.00,52000.00)
//        )
//        arrayListVD.add(
//            ShowMoreDealModel(2,0,R.drawable.vd1_sach,"Bí Mật Hành Trình Tình Yêu",30400.00,48000.00)
//        )
//        arrayListVD.add(
//            ShowMoreDealModel(3,0,R.drawable.vd1_sach,"Dạy Tiếng Anh Xu Hướng Mới",33400.00,56000.00)
//        )
//        showMoreDealAdapter = ShowMoreDealAdapter(arrayListVD)
//        this.recyclerViewSMDeal.adapter = showMoreDealAdapter
//    }

}
