package com.example.kltn.screen.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.kltn.R
import com.google.android.material.tabs.TabLayout


class ProfileFragment : Fragment() {

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_profile, container, false)
        tabLayout = view.findViewById<TabLayout>(R.id.tabs)
        viewPager = view.findViewById<ViewPager>(R.id.viewpager)
//        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
//
//        viewPager!!.setAdapter(MyAdapter(this.fragmentManager!!))
//        tabLayout!!.setupWithViewPager(viewPager)
        setStatePageAdapter()
        return view
    }


//    private inner class MyAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
//        private val int_items = 2
//
//        override fun getItem(position: Int): Fragment {
//            var fragment: Fragment? = null
//            when (position) {
//                0 -> fragment = LoginFragment()
//                    .newInstance()
//                1 -> fragment = RegisterFragment()
//                    .newInstance()
//            }
//            return fragment!!
//        }
//
//        override fun getCount(): Int {
//            return int_items
//        }
//
//
//        override fun getPageTitle(position: Int): CharSequence? {
//            when (position) {
//                0 -> return "ĐĂNG NHẬP"
//                1 -> return "ĐĂNG KÍ"
//            }
//            return null
//        }
//    }

    inner class MyViewPageStateAdapter(fm:FragmentManager): FragmentStatePagerAdapter(fm){
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
        myViewPageStateAdapter.addFragment(LoginFragment(),"ĐĂNG NHẬP")
        myViewPageStateAdapter.addFragment(RegisterFragment(),"ĐĂNG KÍ")
        viewPager!!.adapter=myViewPageStateAdapter
        tabLayout!!.setupWithViewPager(viewPager,true)

    }


}
