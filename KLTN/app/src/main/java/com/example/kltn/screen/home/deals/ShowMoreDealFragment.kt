package com.example.kltn.screen.home.deals

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
import com.example.kltn.screen.home.adapter.FilterAdapter
import com.example.kltn.screen.home.model.FilterModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.custom_toolbar_search.*

/**
 * A simple [Fragment] subclass.
 */
class ShowMoreDealFragment : Fragment() {
    lateinit var recyclerViewFilter: RecyclerView
    lateinit var btnBack: ImageView
    lateinit var filterAdapter: FilterAdapter
    lateinit var btnFilter: ConstraintLayout
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var check = 0
        val view = inflater.inflate(R.layout.fragment_show_more_deal, container, false)
        tabLayout = view.findViewById<TabLayout>(R.id.tab_theloai)
        viewPager = view.findViewById<ViewPager>(R.id.viewpager_show_more_deal)
        btnBack = view.findViewById(R.id.btn_back_showmore)
        btnFilter = view.findViewById(R.id.btn_filter)

        recyclerViewFilter = view.findViewById(R.id.recyclerview_filter)
        recyclerViewFilter.visibility = View.GONE
        setupRecyclerviewFilter()
        btnFilter.setOnClickListener {
            if(check == 0)
            {
                recyclerViewFilter.visibility = View.VISIBLE
                check =1
            }
            else
            {
                recyclerViewFilter.visibility = View.GONE
                check =0
            }
        }
        btnBack.setOnClickListener{
            if ( getFragmentManager()!!.getBackStackEntryCount() > 0)
            {
                getFragmentManager()!!.popBackStack();
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

        }
    }

    private fun setStatePageAdapter() {
        val myViewPageStateAdapter: MyViewPageStateAdapter = MyViewPageStateAdapter(activity!!.supportFragmentManager)
        myViewPageStateAdapter.addFragment(ChildShowMoreDealFragment(0), "Tất cả")
        myViewPageStateAdapter.addFragment(ChildShowMoreDealFragment(1), "Thiếu nhi")
        myViewPageStateAdapter.addFragment(ChildShowMoreDealFragment(2), "Childrens Books")
        myViewPageStateAdapter.addFragment(ChildShowMoreDealFragment(3), "Văn Học")
        viewPager!!.adapter = myViewPageStateAdapter
        tabLayout!!.setupWithViewPager(viewPager, true)
    }
    private fun setupRecyclerviewFilter()
    {
        recyclerViewFilter.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL,false)
        val arrayList = ArrayList<FilterModel>()
        arrayList.add(FilterModel(0,"Bán Chạy Tuần",R.drawable.ic_check_black_24dp))
        arrayList.add(FilterModel(1,"Bán Chạy Tháng",R.drawable.ic_check_black_24dp))
        arrayList.add(FilterModel(2,"Bán Chạy Năm",R.drawable.ic_check_black_24dp))
        filterAdapter = FilterAdapter(activity!!,arrayList)
        recyclerViewFilter.adapter = filterAdapter
    }

}
