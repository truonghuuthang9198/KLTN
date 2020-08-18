package com.example.kltn.screen.home.psychological_skills

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

class PsychologicalSkillsFragment() : Fragment() {
    lateinit var btnShowMore: Button
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_psychological_skills, container, false)
        tabLayout = view.findViewById<TabLayout>(R.id.tab_psychological_skills)
        viewPager = view.findViewById<ViewPager>(R.id.viewpager_psychological_skills)
        btnShowMore = view.findViewById(R.id.btn_showmore_psychologicalskills)
        setStatePageAdapter()
        btnShowMore.setOnClickListener()
        {
            loadFragment(ShowMoreTopicFragment("TL0015"))
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
        myViewPageStateAdapter.addFragment(TabPsychologicalSkillsFragment(0),"Tâm Lý - Kỹ Năng Giá Tốt")
        myViewPageStateAdapter.addFragment(TabPsychologicalSkillsFragment(1),"Tâm Lý Kỹ Năng - Mới")
        myViewPageStateAdapter.addFragment(TabPsychologicalSkillsFragment(2),"Tâm Lý Kỹ Năng - Bán Chạy")
        viewPager!!.offscreenPageLimit =3
        viewPager!!.adapter=myViewPageStateAdapter
        tabLayout!!.setupWithViewPager(viewPager,true)
    }
    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .addToBackStack(null)
                .commit()
            return true
        }
        return false
    }
}