package com.example.kltn.screen.home.deals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.kltn.R
import com.example.kltn.screen.home.adapter.DealAdapter
import com.example.kltn.screen.home.model.DealsModel

/**
 * A simple [Fragment] subclass.
 */
class ChildDealFragment(val tabId: Int) : Fragment() {
    lateinit var recycleviewDeal: RecyclerView
    lateinit var dealAdapter: DealAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_child_deal, container, false)
        recycleviewDeal = view!!.findViewById<RecyclerView>(R.id.recyclerview_deal)
        setUpRecyclerView()
        return view
    }
    fun setUpRecyclerView()
    {
        recycleviewDeal.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.HORIZONTAL,false)
        val arrayList = ArrayList<DealsModel>()
        val listTab0 = ArrayList<DealsModel>()

        arrayList.add(
            DealsModel(0,0,R.drawable.vd_sach,"Dạy Trẻ Biết Đọc Sớm",53000.00,79000.00)
        )
        arrayList.add(
            DealsModel(1,0,R.drawable.vd_sach,"Get Set Go: Mathematics Equals",36400.00,52000.00)
        )
        arrayList.add(
            DealsModel(2,0,R.drawable.vd_sach,"Bí Mật Hành Trình Tình Yêu",30400.00,48000.00)
        )
        arrayList.add(
            DealsModel(3,0,R.drawable.vd_sach,"Dạy Tiếng Anh Xu Hướng Mới",33330.00,56000.00)
        )
        arrayList.add(
            DealsModel(0,1,R.drawable.vd1_sach,"Dạy Trẻ Biết Đọc Sớm",53720.00,79000.00)
        )
        arrayList.add(
            DealsModel(1,1,R.drawable.vd1_sach,"Get Set Go: Mathematics Equals",36000.00,52000.00)
        )
        arrayList.add(
            DealsModel(2,1,R.drawable.vd1_sach,"Bí Mật Hành Trình Tình Yêu",30400.00,48000.00)
        )
        arrayList.add(
            DealsModel(3,1,R.drawable.vd1_sach,"Dạy Tiếng Anh Xu Hướng Mới",33400.00,56000.00)
        )
        arrayList.forEach{
            if(it.tabId == tabId)
            {
                listTab0.add(it)
            }
        }
        dealAdapter = DealAdapter(listTab0)
        recycleviewDeal.adapter = dealAdapter
    }
}
