package com.example.kltn.screen.home.economic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.kltn.R
import com.example.kltn.screen.home.bestbook.ShowMoreBestBookFragment
import com.google.android.material.tabs.TabLayout

class EconomicFragment() : Fragment() {
    lateinit var btnShowMore: Button
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_economic, container, false)
        tabLayout = view.findViewById<TabLayout>(R.id.tab_economic)
        viewPager = view.findViewById<ViewPager>(R.id.viewpager_economic)
        btnShowMore = view.findViewById(R.id.btn_showmore_economic)
        setStatePageAdapter()
        btnShowMore.setOnClickListener()
        {
            loadFragment(ShowMoreBestBookFragment("DM001", "TL002"))
        }
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
        val myViewPageStateAdapter: MyViewPageStateAdapter =
            MyViewPageStateAdapter(activity!!.supportFragmentManager)
        myViewPageStateAdapter.addFragment(TabEconomicFragment(0), "Giảm Sốc - Kinh Tế")
        myViewPageStateAdapter.addFragment(TabEconomicFragment(1), "Kinh Tế - Mới")
        myViewPageStateAdapter.addFragment(TabEconomicFragment(2), "Kinh Tế - Bán Chạy")
        viewPager!!.offscreenPageLimit = 3
        viewPager!!.adapter = myViewPageStateAdapter
        tabLayout!!.setupWithViewPager(viewPager, true)
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            (context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .addToBackStack(null)
                .commit()
            return true
        }
        return false
    }
}