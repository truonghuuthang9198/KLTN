package com.example.kltn.screen.home.deals

import android.content.Context
import android.nfc.Tag
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.kltn.R
import com.example.kltn.screen.cart.model.CartModel
import com.example.kltn.screen.home.adapter.FilterAdapter
import com.example.kltn.screen.home.adapter.FilterAdapter.Companion.title
import com.example.kltn.screen.home.model.FilterModel
import com.google.android.material.tabs.TabLayout
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import kotlinx.android.synthetic.main.custom_toolbar_search.*

/**
 * A simple [Fragment] subclass.
 */
class ShowMoreDealFragment : Fragment(){
    lateinit var recyclerViewFilter: RecyclerView
    lateinit var btnBack: ImageView
    lateinit var filterAdapter: FilterAdapter
    lateinit var titleFilter: TextView
    lateinit var constraint: ConstraintLayout
    lateinit var btnFilter: ConstraintLayout
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private var onActionData: OnActionData<FilterModel>? = null

    companion object {
        var arrayList: ArrayList<FilterModel> = ArrayList<FilterModel>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var check = 0
        val view = inflater.inflate(R.layout.fragment_show_more_deal, container, false)
        tabLayout = view.findViewById<TabLayout>(R.id.tab_theloai)
        viewPager = view.findViewById<ViewPager>(R.id.viewpager_show_more_deal)
        btnBack = view.findViewById(R.id.btn_back_showmore)
        constraint = view.findViewById(R.id.constraint_visible)
        btnFilter = view.findViewById(R.id.btn_filter)
        titleFilter = view.findViewById(R.id.title_filter_deal)
        constraint.visibility = View.GONE
        titleFilter.text = title
        recyclerViewFilter = view.findViewById(R.id.recyclerview_filter)
        recyclerViewFilter.visibility = View.GONE
        setupRecyclerviewFilter()
        btnFilter.setOnClickListener {
            if(check == 0)
            {
                recyclerViewFilter.visibility = View.VISIBLE
                constraint.visibility = View.VISIBLE
                check =1
            }
            else
            {
                recyclerViewFilter.visibility = View.GONE
                constraint.visibility = View.GONE
                check =0
            }
        }
        constraint.setOnClickListener{
            if(check == 1)
            {
                recyclerViewFilter.visibility = View.GONE
                constraint.visibility = View.GONE
                check = 0
            }
            else
            {
                constraint.visibility = View.GONE
            }
        }
        btnBack.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }
        setStatePageAdapter()
        return view
    }

    inner class MyViewPageStateAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        val fragmentList: MutableList<Fragment> = ArrayList<Fragment>()
        val fragmentTitleList: MutableList<String> = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return fragmentList.get(position)
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitleList.get(position)
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            fragmentTitleList.add(title)
            notifyDataSetChanged()
        }

    }

    private fun setStatePageAdapter() {
        val myViewPageStateAdapter = MyViewPageStateAdapter(activity!!.supportFragmentManager)
        myViewPageStateAdapter.addFragment(ChildShowMoreDealFragment(0), "Tất cả")
        myViewPageStateAdapter.addFragment(ChildShowMoreDealFragment(1), "Childrens Book")
        myViewPageStateAdapter.addFragment(ChildShowMoreDealFragment(2), "Thiếu nhi")
        myViewPageStateAdapter.addFragment(ChildShowMoreDealFragment(3), "Tâm Lý Kỹ - Năng Sống")
        myViewPageStateAdapter.addFragment(ChildShowMoreDealFragment(4), "Văn Học")
//        myViewPageStateAdapter.addFragment(ChildShowMoreDealFragment(2), "Childrens Books")
//        myViewPageStateAdapter.addFragment(ChildShowMoreDealFragment(3), "Văn Học")
        viewPager!!.adapter = myViewPageStateAdapter
        viewPager!!.adapter!!.notifyDataSetChanged()
        tabLayout!!.setupWithViewPager(viewPager, true)
    }
    private fun setupRecyclerviewFilter()
    {
        recyclerViewFilter.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL,false)
        val arrayList1 = ArrayList<FilterModel>()
        if(arrayList.isEmpty()) {
            arrayList1.add(FilterModel(0, "Bán Chạy Tuần", R.drawable.ic_check_black_24dp))
            arrayList1.add(FilterModel(1, "Bán Chạy Tháng", R.drawable.ic_check_black_24dp))
            arrayList1.add(FilterModel(2, "Bán Chạy Năm", R.drawable.ic_check_black_24dp))
            arrayList1.add(FilterModel(3, "Nổi Bật Tuần", R.drawable.ic_check_black_24dp))
            arrayList1.add(FilterModel(4, "Nổi Bật Tháng", R.drawable.ic_check_black_24dp))
            arrayList1.add(FilterModel(5, "Nổi Bật Năm", R.drawable.ic_check_black_24dp))
            arrayList1.add(FilterModel(6, "Mới Nhất", R.drawable.ic_check_black_24dp))
            arrayList =arrayList1
        }
        onActionData = object : OnActionData<FilterModel>{
            override fun onAction(data: FilterModel) {
                titleFilter.text = data.titleFilter
                setStatePageAdapter()
            }

        }
        filterAdapter = FilterAdapter(activity!!,arrayList,onActionData!!)
        recyclerViewFilter.adapter = filterAdapter
    }

//    override fun onCallBack() {
//        this.titleFilter.text = title
//    }

}
