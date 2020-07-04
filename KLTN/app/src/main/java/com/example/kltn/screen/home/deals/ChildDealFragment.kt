package com.example.kltn.screen.home.deals

import com.example.kltn.screen.retrofit.reponse.SachResponse
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.kltn.R
import com.example.kltn.screen.home.adapter.DealAdapter
import com.example.kltn.screen.home.model.DealModel
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        recycleviewDeal.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL, false
        )
        loadListSach()
        //setUpRecyclerView()
        return view
    }

    fun setUpRecyclerView() {
        val arrayList = ArrayList<DealModel>()
        val listTab0 = ArrayList<DealModel>()
        arrayList.add(
            DealModel(
                0,
                0,
                "Khong",
                25000.00,
                0.5,
                "it.hinhAnh",
                "it.kichThuoc",
                "it.loaiBia",
                "it.maCongTy",
                "SACH002",
                "it.maTacGia",
                "it.maTheLoai",
                "it.ngayXuatBan",
                "it.maNhaXuatBan",
                2,
                "it.soTrang",
                "Get Set Go: Mathematics Equals",
                "it.tinhTrang"
            )
        )
        arrayList.add(
            DealModel(
                0,
                0,
                "Khong",
                28000.00,
                0.2,
                "it.hinhAnh",
                "it.kichThuoc",
                "it.loaiBia",
                "it.maCongTy",
                "SACH003",
                "it.maTacGia",
                "it.maTheLoai",
                "it.ngayXuatBan",
                "it.maNhaXuatBan",
                2,
                "it.soTrang",
                "Dạy Tiếng Anh Xu Hướng Mới",
                "it.tinhTrang"
            )
        )
        arrayList.add(
            DealModel(
                0,
                0,
                "Khong",
                20000.00,
                0.3,
                "it.hinhAnh",
                "it.kichThuoc",
                "it.loaiBia",
                "it.maCongTy",
                "SACH004",
                "it.maTacGia",
                "it.maTheLoai",
                "it.ngayXuatBan",
                "it.maNhaXuatBan",
                5,
                "it.soTrang",
                "Bí Mật Hành Trình Tình Yêu",
                "it.tinhTrang"
            )
        )
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
        arrayList.forEach {
            if (it.tabId == tabId) {
                listTab0.add(it)
            }
        }
        dealAdapter = DealAdapter(listTab0)
        recycleviewDeal.adapter = dealAdapter
    }

    private fun loadListSach() {
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
                Log.d("ThangTruong", response.body().toString())

            }
        })

    }

    private fun listSach(response: List<SachResponse>) {
        var id = 0
        var tabid = 0
        val listSachAdapter = ArrayList<DealModel>()
        response.forEach {
            listSachAdapter.add(
                DealModel(
                    id,
                    tabid,
                    it.ghiChu,
                    it.giaBan,
                    it.giamGia,
                    it.hinhAnh,
                    it.kichThuoc,
                    it.loaiBia,
                    it.maCongTy,
                    it.maSach,
                    it.maTacGia,
                    it.maTheLoai,
                    it.ngayXuatBan,
                    it.maNhaXuatBan,
                    it.soLuong,
                    it.soTrang,
                    it.tenSach,
                    it.tinhTrang
                )
            )
            id++
            if (id >= 5 && tabid <= 2) {
                tabid++
            }
        }
        val listAddInTab = ArrayList<DealModel>()
        listSachAdapter.forEach {
            if (tabId == it.tabId) {
                listAddInTab.add(it)
            }
        }
        dealAdapter = DealAdapter(listAddInTab)
        recycleviewDeal.adapter = dealAdapter
    }
}
