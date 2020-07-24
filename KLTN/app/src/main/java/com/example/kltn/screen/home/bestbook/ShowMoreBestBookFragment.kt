package com.example.kltn.screen.home.bestbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowMoreBestBookFragment(val maCategory: String, val maTL: String) : Fragment() {
    lateinit var recyclerViewSM: RecyclerView
    lateinit var recyclerViewFilter: RecyclerView
    lateinit var filterAdapter: FilterCategoryAdapter
    lateinit var titleFilter: TextView
    lateinit var constraint: ConstraintLayout
    lateinit var btnFilter: ConstraintLayout
    lateinit var btn_back_showmore: ImageView
    lateinit var btn_back_home_showmore: ImageView
    private var onActionData: OnActionData<FilterTheLoaiModel>? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_showmore_bestbook, container, false)
        var check = 0
        recyclerViewSM = view.findViewById(R.id.recyclerview_show_more_book)
        constraint = view.findViewById(R.id.constraint_visible_showmore)
        btnFilter = view.findViewById(R.id.btn_filter_category)
        titleFilter = view.findViewById(R.id.title_filter_book)
        btn_back_showmore = view.findViewById(R.id.btn_back_showmore)
        btn_back_home_showmore = view.findViewById(R.id.btn_back_home_showmore)
        constraint.visibility = View.GONE
        recyclerViewFilter = view.findViewById(R.id.recyclerview_filter_category)
        recyclerViewFilter.visibility = View.GONE
        setupRecyclerviewFilter()
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
        setUpRecyclerView()
        return view
    }

    fun setUpRecyclerView() {
        recyclerViewSM.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL, false
        )
        recyclerViewSM.layoutManager = GridLayoutManager(activity, 2)
    }

    private fun setupRecyclerviewFilter() {
        recyclerViewFilter.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
        val arrrayListFilter = ArrayList<FilterTheLoaiModel>()
        val service =
            RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.getListCategory()
        call?.enqueue(object : Callback<List<CategoryResponse>> {
            override fun onFailure(call: Call<List<CategoryResponse>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<CategoryResponse>>,
                response: Response<List<CategoryResponse>>
            ) {
                response.body()!!.forEach {
                    if (it.maDanhMuc == maCategory) {
                        it.theLoais.forEachIndexed { index, theLoai ->
                            arrrayListFilter.add(
                                FilterTheLoaiModel(
                                    index,
                                    theLoai.maTheLoai,
                                    theLoai.tenTheLoai,
                                    R.drawable.ic_check_black_24dp
                                )
                            )
                        }

                        onActionData = object : OnActionData<FilterTheLoaiModel> {
                            override fun onAction(data: FilterTheLoaiModel) {
                                titleFilter.text = data.tenTheLoai
                            }
                        }

                        arrrayListFilter.forEach {
                            if (it.maTheLoai == maTL)
                            {
                                titleFilter.text = it.tenTheLoai
                                it.choose = true
                            }
                        }

                        filterAdapter =
                            FilterCategoryAdapter(context, arrrayListFilter, onActionData!!)
                        recyclerViewFilter.adapter = filterAdapter
                    }
                }
            }
        })

    }

}