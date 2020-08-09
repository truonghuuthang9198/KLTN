package com.example.kltn.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.event.OnActionData
import com.example.kltn.screen.home.adapter.FilterAdapter
import com.example.kltn.screen.home.adapter.FilterCategoryAdapter
import com.example.kltn.screen.home.adapter.ShowMoreDealAdapter
import com.example.kltn.screen.home.model.BookModel
import com.example.kltn.screen.home.model.FilterModel
import com.example.kltn.screen.home.model.FilterTheLoaiModel
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.reponse.CategoryResponse
import com.example.kltn.screen.retrofit.reponse.SachResponse
import com.example.kltn.screen.retrofit.reponse.StatisticalResponse
import com.example.kltn.screen.retrofit.request.StatisticalRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowMoreTopicFragment(val maTL: String) : Fragment() {
    lateinit var recyclerViewSM: RecyclerView
    lateinit var recyclerViewFilter: RecyclerView
    lateinit var filterAdapter: FilterAdapter
    lateinit var titleFilter: TextView
    lateinit var constraint: ConstraintLayout
    lateinit var btnFilter: ConstraintLayout
    lateinit var btn_back_showmore: ImageView
    lateinit var btn_back_home_showmore: ImageView
    lateinit var progressBarHolder:ProgressBar
    private var check=0
    private var onActionData: OnActionData<FilterModel>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_showmore_topic, container, false)
        setUpView(view)

        btn_back_showmore.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }
        btnFilter.setOnClickListener {
            if (check == 0) {
                recyclerViewFilter.visibility = View.VISIBLE
                constraint.visibility = View.VISIBLE
                check = 1
            } else {
                recyclerViewFilter.visibility = View.GONE
                constraint.visibility = View.GONE
                check = 0
            }
        }
        constraint.setOnClickListener {
            if (check == 1) {
                recyclerViewFilter.visibility = View.GONE
                constraint.visibility = View.GONE
                check = 0
            } else {
                constraint.visibility = View.GONE
            }
        }
        setupRecyclerviewFilter()
        selectTheLoai(maTL)
        return view
    }

    fun setUpView(view: View) {
        progressBarHolder = view.findViewById(R.id.progressBarHolder)
        recyclerViewSM = view.findViewById(R.id.recyclerview_book)
        constraint = view.findViewById(R.id.constraint_visible_showmore)
        btnFilter = view.findViewById(R.id.btn_fillter_book)
        titleFilter = view.findViewById(R.id.title_filter_book)
        btn_back_showmore = view.findViewById(R.id.btn_back_showmore)
        btn_back_home_showmore = view.findViewById(R.id.btn_back_home_showmore)
        constraint.visibility = View.GONE
        recyclerViewFilter = view.findViewById(R.id.recyclerview_filter_book)
        recyclerViewFilter.visibility = View.GONE
        recyclerViewSM.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL, false
        )
        recyclerViewSM.layoutManager = GridLayoutManager(activity, 2)
    }



    fun selectTheLoai(theloai: String) {
        progressBarHolder.visibility = View.VISIBLE
        val service =
            RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.getSachTheoTL(theloai)
        call?.enqueue(object : Callback<List<SachResponse>> {
            override fun onFailure(call: Call<List<SachResponse>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<SachResponse>>,
                response: Response<List<SachResponse>>
            ) {
                progressBarHolder.visibility = View.GONE
                setUpRecyclerviewBook(response)
            }
        })
    }

    fun setUpRecyclerviewBook(response: Response<List<SachResponse>>) {
        val listBook = ArrayList<BookModel>()
        response.body()!!.forEachIndexed { index, it ->
            listBook.add(
                BookModel(
                    index,
                    0,
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
        }
        val adapter = ShowMoreDealAdapter(listBook)
        recyclerViewSM.adapter = adapter
    }
    private fun setupRecyclerviewFilter() {
        recyclerViewFilter.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
        val arrrayListFilter = ArrayList<FilterModel>()
        if (arrrayListFilter.isEmpty()) {
            arrrayListFilter.add(
                FilterModel(
                    0,
                    4,
                    "Tất cả",
                    R.drawable.ic_check_black_24dp
                )
            )
            arrrayListFilter.add(
                FilterModel(
                    0,
                    0,
                    "Bán Chạy Tuần",
                    R.drawable.ic_check_black_24dp
                )
            )
            arrrayListFilter.add(
                FilterModel(
                    0,
                    1,
                    "Bán Chạy Tháng",
                    R.drawable.ic_check_black_24dp
                )
            )
            arrrayListFilter.add(
                FilterModel(
                    0,
                    2,
                    "Bán Chạy Năm",
                    R.drawable.ic_check_black_24dp
                )
            )
        }
        onActionData = object : OnActionData<FilterModel> {
            override fun onAction(data: FilterModel) {
                titleFilter.text = data.titleFilter
                if(data.id == 4)
                {
                    selectTheLoai(maTL)
                }
                else {
                    val statisticalRequest = StatisticalRequest(maTL)
                    getListThongKe(data.id.toString(), statisticalRequest)
                }
                recyclerViewFilter.visibility = View.GONE
                constraint.visibility = View.GONE
                check =0
            }
        }
        filterAdapter = FilterAdapter(context,arrrayListFilter,onActionData!!)
        recyclerViewFilter.adapter = filterAdapter

    }

    fun getListThongKe(id:String,statisticalRequest: StatisticalRequest) {
        progressBarHolder.visibility = View.VISIBLE
        val service =
            RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.getListThongKe(id,statisticalRequest)
        call?.enqueue(object : Callback<List<StatisticalResponse>> {
            override fun onFailure(call: Call<List<StatisticalResponse>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<StatisticalResponse>>,
                response: Response<List<StatisticalResponse>>
            ) {
                progressBarHolder.visibility = View.GONE
                setUpRecyclerviewBookTK(response)
            }
        })
    }
    fun setUpRecyclerviewBookTK(response: Response<List<StatisticalResponse>>) {
        val listBook = ArrayList<BookModel>()
        response.body()!!.forEachIndexed { index, it ->
            listBook.add(
                BookModel(
                    index,
                    0,
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
                    it.ngayXuatBan.toString(),
                    it.nhaXuatBan.tenNhaXuatBan,
                    it.soLuong,
                    it.soTrang,
                    it.tenSach,
                    it.tinhTrang,
                    it.soSao
                )
            )
        }
        val adapter = ShowMoreDealAdapter(listBook)
        recyclerViewSM.adapter = adapter
    }


}