package com.example.kltn.screen.home.deals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.kltn.R
import com.example.kltn.screen.home.adapter.DealAdapter
import com.example.kltn.screen.home.model.DealsModel
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.reponse.SachReponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class ChildDealFragment(val tabId: Int) : Fragment() {
    lateinit var recycleviewDeal: RecyclerView
    lateinit var dealAdapter: DealAdapter
    var listSachAdapter= ArrayList<DealsModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_child_deal, container, false)
        recycleviewDeal = view!!.findViewById<RecyclerView>(R.id.recyclerview_deal)
        loadListSach()
        setUpRecyclerView()
        return view
    }
    fun setUpRecyclerView()
    {
        recycleviewDeal.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.HORIZONTAL,false)
        val arrayList = ArrayList<DealsModel>()
        val listTab0 = ArrayList<DealsModel>()

//        arrayList.add(
//            DealsModel(0,0,R.drawable.vd3_sach,"Dạy Trẻ Biết Đọc Sớm",53000.00,79000.00)
//        )
//        arrayList.add(
//            DealsModel(1,0,R.drawable.vd3_sach,"Get Set Go: Mathematics Equals",36400.00,52000.00)
//        )
//        arrayList.add(
//            DealsModel(2,0,R.drawable.vd3_sach,"Bí Mật Hành Trình Tình Yêu",30400.00,48000.00)
//        )
//        arrayList.add(
//            DealsModel(3,0,R.drawable.vd3_sach,"Dạy Tiếng Anh Xu Hướng Mới",33330.00,56000.00)
//        )
//        arrayList.add(
//            DealsModel(0,1,R.drawable.vd3_sach,"Dạy Trẻ Biết Đọc Sớm",53720.00,79000.00)
//        )
//        arrayList.add(
//            DealsModel(1,1,R.drawable.vd3_sach,"Get Set Go: Mathematics Equals",36000.00,52000.00)
//        )
//        arrayList.add(
//            DealsModel(2,1,R.drawable.vd3_sach,"Bí Mật Hành Trình Tình Yêu",30400.00,48000.00)
//        )
//        arrayList.add(
//            DealsModel(3,1,R.drawable.vd3_sach,"Dạy Tiếng Anh Xu Hướng Mới",33400.00,56000.00)
//        )
        arrayList.forEach{
            if(it.tabId == tabId)
            {
                listTab0.add(it)
            }
        }
        dealAdapter = DealAdapter(listTab0)
        recycleviewDeal.adapter = dealAdapter
    }
    private fun loadListSach()
    {
        val list = mutableListOf<DealsModel>()
        val listTab0 = ArrayList<DealsModel>()
        val service = RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.getListSach()
        call?.enqueue(object : Callback<List<SachReponse>> {
            override fun onFailure(call: Call<List<SachReponse>>, t: Throwable) {
                Toast.makeText(activity!!,  t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<SachReponse>>,
                response: Response<List<SachReponse>>
            ) {
                listSach(response.body()!!)
            }

        })

    }
    private fun listSach(list: List<SachReponse>)
    {

        list.forEach {
            listSachAdapter.add(DealsModel(0,0,it.hinhAnh,it.tenSach,it.giaBan,it.giaBan))
        }
        dealAdapter = DealAdapter(listSachAdapter)
        recycleviewDeal.adapter = dealAdapter
        dealAdapter.notifyDataSetChanged()
    }
}
