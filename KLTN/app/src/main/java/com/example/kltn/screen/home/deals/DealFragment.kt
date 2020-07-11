package com.example.kltn.screen.home.deals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.kltn.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_deals.*

/**
 * A simple [Fragment] subclass.
 */
class DealFragment() : Fragment() {
    lateinit var btnShowMore: Button
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_deals, container, false)
        tabLayout = view.findViewById<TabLayout>(R.id.tab_deal)
        viewPager = view.findViewById<ViewPager>(R.id.viewpager_deal)
        btnShowMore = view.findViewById(R.id.btn_showmore)
        setStatePageAdapter()
        btnShowMore.setOnClickListener()
        {
            loadFragment(ShowMoreDealFragment())
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

        fun addFragment(fragment:Fragment,title:String){
            fragmentList.add(fragment)
            fragmentTitleList.add(title)

        }
    }
    private fun setStatePageAdapter(){
        val myViewPageStateAdapter: MyViewPageStateAdapter = MyViewPageStateAdapter(activity!!.supportFragmentManager)
        myViewPageStateAdapter.addFragment(ChildDealFragment(0),"Deal Hot Theo Ngày")
        myViewPageStateAdapter.addFragment(ChildDealFragment(1),"Sách HOT - Giảm sốc")
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
