package com.example.kltn.screen.home.literary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.kltn.R
import com.google.android.material.tabs.TabLayout

class LiteraryFragment : Fragment() {
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_literary, container, false)
        tabLayout = view.findViewById<TabLayout>(R.id.tab_literary)
        viewPager = view.findViewById<ViewPager>(R.id.viewpager_literary)
        setStatePageAdapter()
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
        myViewPageStateAdapter.addFragment(TabLiteraryFragment(0),"Văn Học - Giá Tốt")
        myViewPageStateAdapter.addFragment(TabLiteraryFragment(1),"Truyện ngắn - Tản Văn")
        myViewPageStateAdapter.addFragment(TabLiteraryFragment(2),"Ngôn Tình")
        viewPager!!.adapter=myViewPageStateAdapter
        viewPager!!.offscreenPageLimit = 3
        tabLayout!!.setupWithViewPager(viewPager,true)
    }
}