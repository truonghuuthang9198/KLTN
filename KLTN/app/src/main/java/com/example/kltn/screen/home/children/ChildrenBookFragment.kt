package com.example.kltn.screen.home.children

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
import com.example.kltn.screen.home.bestbook.TabBestBookFragment
import com.google.android.material.tabs.TabLayout

class ChildrenBookFragment : Fragment() {
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    lateinit var btn_showmore_children_book: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_children_book, container, false)
        tabLayout = view.findViewById<TabLayout>(R.id.tab_children_book)
        viewPager = view.findViewById<ViewPager>(R.id.viewpager_children_book)
        btn_showmore_children_book = view.findViewById(R.id.btn_showmore_children_book)
        btn_showmore_children_book.setOnClickListener {
            loadFragment(ShowMoreBestBookFragment("DM001","TL003"))
        }
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
        myViewPageStateAdapter.addFragment(TabChildrenBookFragment(0),"Sách Thiếu Nhi Giá Tốt")
        myViewPageStateAdapter.addFragment(TabChildrenBookFragment(1),"Truyện Đọc Thiếu Nhi")
        viewPager!!.adapter=myViewPageStateAdapter
        viewPager!!.offscreenPageLimit = 2
        tabLayout!!.setupWithViewPager(viewPager,true)

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