package com.example.kltn.screen.home.psychological_skills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.home.adapter.EconomicAdapter
import com.example.kltn.screen.home.adapter.PsychologicalSkillAdapter
import com.example.kltn.screen.home.model.BookModel
import com.example.kltn.screen.retrofit.reponse.SachResponse

class TabPsychologicalSkillsFragment(val tabId: Int) : Fragment() {
    lateinit var recyclerviewPsychologicalSkills: RecyclerView
    lateinit var psychologicalSkillAdapter: PsychologicalSkillAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tab_fragment_psychological_skills, container, false)
        recyclerviewPsychologicalSkills = view!!.findViewById<RecyclerView>(R.id.recyclerview_psychological_skills)
        recyclerviewPsychologicalSkills.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL, false
        )
//        loadListSach()
        setUpRecyclerView()
        return view
    }

    fun setUpRecyclerView() {
        val arrayList = ArrayList<BookModel>()
        val listTab0 = ArrayList<BookModel>()

        arrayList.forEach {
            if (it.tabId == tabId) {
                listTab0.add(it)
            }
        }
        psychologicalSkillAdapter = PsychologicalSkillAdapter(listTab0)
        recyclerviewPsychologicalSkills.adapter = psychologicalSkillAdapter
    }

//    private fun loadListSach() {
//        val service = RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
//        val call = service?.getListSach()
//        call?.enqueue(object : Callback<List<SachResponse>> {
//            override fun onFailure(call: Call<List<SachResponse>>, t: Throwable) {
//                Log.d("ThangTruong", t.message)
//            }
//
//            override fun onResponse(
//                call: Call<List<SachResponse>>,
//                response: Response<List<SachResponse>>
//            ) {
//                listSach(response.body()!!)
//                Log.d("ThangTruong", response.body().toString())
//
//            }
//        })
//
//    }

//    private fun listSach(response: List<SachResponse>) {
//        var id = 0
//        var tabid = 0
//        val listSachAdapter = ArrayList<BookModel>()
//        response.forEach {
//            listSachAdapter.add(
//                BookModel(
//                    id,
//                    tabid,
//                    it.ghiChu,
//                    it.giaBan,
//                    it.giamGia,
//                    it.hinhAnh,
//                    it.kichThuoc,
//                    it.loaiBia,
//                    it.congTyPhatHanh.tenCongTy,
//                    it.maSach,
//                    it.tacGia.tenTacGia,
//                    it.theLoai.tenTheLoai,
//                    it.ngayXuatBan,
//                    it.nhaXuatBan.tenNhaXuatBan,
//                    it.soLuong,
//                    it.soTrang,
//                    it.tenSach,
//                    it.tinhTrang,
//                    it.soSao
//                )
//            )
//            id++
//            if (id >= 5 && tabid <= 2) {
//                tabid++
//            }
//        }
//        val listAddInTab = ArrayList<BookModel>()
//        listSachAdapter.forEach {
//            if (tabId == it.tabId) {
//                listAddInTab.add(it)
//            }
//        }
//        economicAdapter = EconomicAdapter(listAddInTab)
//        recycleviewEconomic.adapter = economicAdapter
//    }
}