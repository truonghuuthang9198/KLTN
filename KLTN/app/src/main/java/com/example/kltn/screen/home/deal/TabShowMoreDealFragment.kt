package com.example.kltn.screen.home.deal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.kltn.R
import com.example.kltn.screen.home.adapter.FilterAdapter
import com.example.kltn.screen.home.adapter.ShowMoreDealAdapter
import com.example.kltn.screen.home.model.BookModel
import com.example.kltn.screen.home.model.FilterModel
import com.example.kltn.screen.event.OnActionData

class TabShowMoreDealFragment(val tabId: Int) : Fragment() {
    lateinit var recyclerViewSMDeal: RecyclerView
    lateinit var recyclerViewFilter: RecyclerView
    lateinit var showMoreDealAdapter: ShowMoreDealAdapter
    lateinit var filterAdapter: FilterAdapter
    lateinit var titleFilter: TextView
    lateinit var constraint: ConstraintLayout
    lateinit var btnFilter: ConstraintLayout
    private var onActionData: OnActionData<FilterModel>? = null

//    companion object {
//
//        private val KEY_TAB = "KEY_TAB"
//
//        fun newInstance(tabId: Int): ChildShowMoreDealFragment{
//            val receipe = ChildShowMoreDealFragment()
//            val bundle = Bundle()
//            bundle.putInt(KEY_TAB, tabId)
//            receipe.arguments = bundle
//            return receipe
//        }
//
//

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var check = 0

        recyclerViewSMDeal = view.findViewById(R.id.recyclerview_show_more_deal)
        constraint = view.findViewById(R.id.constraint_visible)
        btnFilter = view.findViewById(R.id.btn_filter)
        titleFilter = view.findViewById(R.id.title_filter_deal)
        constraint.visibility = View.GONE
        titleFilter.text = FilterAdapter.title
        recyclerViewFilter = view.findViewById(R.id.recyclerview_filter)
        recyclerViewFilter.visibility = View.GONE
        setupRecyclerviewFilter()
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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val tabId = this.arguments?.getInt(KEY_TAB)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_child_show_more_deal, container, false)
        return view
    }

    fun setUpRecyclerView() {
        recyclerViewSMDeal.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL, false
        )
        recyclerViewSMDeal.layoutManager = GridLayoutManager(activity, 2)
        val arrayList = ArrayList<BookModel>()
        val listTab0 = ArrayList<BookModel>()

        arrayList.forEach {
            if (it.tabId == tabId) {
                listTab0.add(it)
            }
        }
        showMoreDealAdapter = ShowMoreDealAdapter(listTab0)
        recyclerViewSMDeal.adapter = showMoreDealAdapter
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
                        tabId,
                        0,
                        "Bán Chạy Tuần",
                        R.drawable.ic_check_black_24dp
                    )
                )
                arrrayListFilter.add(
                    FilterModel(
                        tabId,
                        1,
                        "Bán Chạy Tháng",
                        R.drawable.ic_check_black_24dp
                    )
                )
                arrrayListFilter.add(
                    FilterModel(
                        tabId,
                        2,
                        "Bán Chạy Năm",
                        R.drawable.ic_check_black_24dp
                    )
                )
                arrrayListFilter.add(
                    FilterModel(
                        tabId,
                        3,
                        "Nổi Bật Tuần",
                        R.drawable.ic_check_black_24dp
                    )
                )
                arrrayListFilter.add(
                    FilterModel(
                        tabId,
                        4,
                        "Nổi Bật Tháng",
                        R.drawable.ic_check_black_24dp
                    )
                )
                arrrayListFilter.add(
                    FilterModel(
                        tabId,
                        5,
                        "Nổi Bật Năm",
                        R.drawable.ic_check_black_24dp
                    )
                )
                arrrayListFilter.add(
                    FilterModel(
                        tabId,
                        6,
                        "Mới Nhất",
                        R.drawable.ic_check_black_24dp
                    )
                )
        }

        onActionData = object : OnActionData<FilterModel> {
            override fun onAction(data: FilterModel) {
                titleFilter.text = data.titleFilter
            }
        }
        filterAdapter = FilterAdapter(context, arrrayListFilter, onActionData!!, tabId)
        recyclerViewFilter.adapter = filterAdapter
    }


}
