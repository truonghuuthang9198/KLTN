package com.example.kltn.screen.home.deal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.home.adapter.DealAdapter
import com.example.kltn.screen.home.model.BookModel
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.reponse.SachResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class TabDealFragment(val tabId: Int) : Fragment() {
    lateinit var recycleviewDeal: RecyclerView
    lateinit var dealAdapter: DealAdapter

    lateinit var progressBarHolder: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tab_fragment_deal, container, false)
        progressBarHolder = view.findViewById(R.id.progressBarHolder)
        recycleviewDeal = view.findViewById<RecyclerView>(R.id.recyclerview_deal)
        recycleviewDeal.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL, false
        )
        loadListSach()
        //setUpRecyclerView()
        return view
    }



    private fun loadListSach() {
        progressBarHolder.visibility = View.VISIBLE
        val service = RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.getListSach()
        call?.enqueue(object : Callback<List<SachResponse>> {
            override fun onFailure(call: Call<List<SachResponse>>, t: Throwable) {
                Log.d("ThangTruong", t.message)
            }

            override fun onResponse(
                call: Call<List<SachResponse>>,
                response: Response<List<SachResponse>>
            ) {
                listSach(response.body()!!)
                progressBarHolder.visibility = View.GONE
            }
        })

    }

    private fun listSach(response: List<SachResponse>) {
        var id = 0
        var tabid = 0
        val listSachAdapter = ArrayList<BookModel>()
        response.forEach {
            listSachAdapter.add(
                BookModel(
                    id,
                    tabid,
                    it.ghiChu,
                    it.giaBan,
                    it.giamGia,
                    it.hinhAnh,
                    it.kichThuoc,
                    it.loaiBia,
                    it.congTyPhatHanh.tenCongTy,
                    it.maSach,
                    it.tacGia.tenTacGia,
                    it.theLoai.tenTheLoai,
                    it.theLoai.maTheLoai,
                    it.ngayXuatBan,
                    it.nhaXuatBan.tenNhaXuatBan,
                    it.soLuong,
                    it.soTrang,
                    it.tenSach,
                    it.tinhTrang,
                    it.soSao
                )
            )
            id++
            if (id%5 == 0) {
                if(tabid<2) {
                    tabid++
                }
            }
        }
        val listAddInTab = ArrayList<BookModel>()
        listSachAdapter.forEach {
            if (tabId == it.tabId) {
                listAddInTab.add(it)
            }
        }
        dealAdapter = DealAdapter(listAddInTab)
        recycleviewDeal.adapter = dealAdapter
    }
}
