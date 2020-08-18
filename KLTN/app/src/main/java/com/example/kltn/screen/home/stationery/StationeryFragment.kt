package com.example.kltn.screen.home.stationery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.kltn.R
import com.example.kltn.screen.home.ShowMoreTopicFragment
import com.google.android.material.tabs.TabLayout

class StationeryFragment() : Fragment() {
    lateinit var btnShowMore: Button
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_stationery, container, false)
        tabLayout = view.findViewById<TabLayout>(R.id.tab_stationery)
        viewPager = view.findViewById<ViewPager>(R.id.viewpager_stationery)
        btnShowMore = view.findViewById(R.id.btn_showmore_stationery)
        setStatePageAdapter()
        btnShowMore.setOnClickListener()
        {
            loadFragment(ShowMoreTopicFragment("TL0016"))
        }
        return view
    }
    inner class MyViewPageStateAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){
        val fragmentList:MutableList<Fragment> = ArrayList<Fragment>()
        val fragmentTitleList:MutableList<String> = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return fragmentList.get(position)
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitleList.get(position)
        }

        fun addFragment(fragment: Fragment, title:String){
            fragmentList.add(fragment)
            fragmentTitleList.add(title)

        }
    }
    private fun setStatePageAdapter(){
        val myViewPageStateAdapter: MyViewPageStateAdapter = MyViewPageStateAdapter(activity!!.supportFragmentManager)
        myViewPageStateAdapter.addFragment(TabStationeryFragment(0),"VPP Giá Tốt")
        myViewPageStateAdapter.addFragment(TabStationeryFragment(1),"Bìa - File Hồ Sơ")
        viewPager!!.offscreenPageLimit =2
        viewPager!!.adapter=myViewPageStateAdapter
        tabLayout!!.setupWithViewPager(viewPager,true)
    }
    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment,"ShowMoreDealFragment")
                .addToBackStack(null)
                .commit()
            return true
        }
        return false
    }
}