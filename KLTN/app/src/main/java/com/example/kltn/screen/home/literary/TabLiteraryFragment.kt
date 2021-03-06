package com.example.kltn.screen.home.literary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.home.adapter.DealAdapter
import com.example.kltn.screen.home.adapter.LiteraryAdapter
import com.example.kltn.screen.home.model.BookModel
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.reponse.SachResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TabLiteraryFragment(val tabId: Int) : Fragment() {
    lateinit var recycleviewLiterary: RecyclerView
    lateinit var literaryAdapter: LiteraryAdapter
    lateinit var progressBarHolder: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tab_fragment_literary, container, false)
        recycleviewLiterary = view.findViewById(R.id.recyclerview_literary)
        recycleviewLiterary.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL, false
        )
        progressBarHolder = view.findViewById(R.id.progressBarHolder)
        loadListSach()
        //setUpRecyclerView()
        return view
    }

    fun setUpRecyclerView() {
        recycleviewLiterary.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL, false
        )
        val arrayList = ArrayList<BookModel>()
        val listTab0 = ArrayList<BookModel>()

        arrayList.forEach {
            if (it.tabId == tabId) {
                listTab0.add(it)
            }
        }
        literaryAdapter = LiteraryAdapter(listTab0)
        recycleviewLiterary.adapter = literaryAdapter
    }

    private fun loadListSach() {
        progressBarHolder.visibility = View.VISIBLE
        val service = RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.getSachTheoTL("TL001")
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
                if(tabid<3) {
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
        literaryAdapter = LiteraryAdapter(listAddInTab)
        recycleviewLiterary.adapter = literaryAdapter
    }

}