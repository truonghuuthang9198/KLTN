package com.example.kltn.screen.home.deals

import android.os.Bundle
import android.util.SparseArray
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.kltn.R
import com.example.kltn.screen.home.model.TabItemModel
import com.google.android.material.tabs.TabLayout

/**
 * A simple [Fragment] subclass.
 */
class ShowMoreDealFragment() : Fragment() {

    lateinit var btnBack: ImageView
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null

    companion object {
        var listTemp: ArrayList<TabItemModel> = ArrayList()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_show_more_deal, container, false)
        tabLayout = view.findViewById<TabLayout>(R.id.tab_theloai)
        viewPager = view.findViewById<ViewPager>(R.id.viewpager_show_more_deal)
        btnBack = view.findViewById(R.id.btn_back_showmore)
        btnBack.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }
        val adapter = ViewPagerAdapter(activity!!.supportFragmentManager)
//        setStatePageAdapter()
        val list: ArrayList<TabItemModel> = ArrayList()
        list.add(TabItemModel(0, "Tất Cả"))
        list.add(TabItemModel(1, "Childrens Book"))
        list.add(TabItemModel(2, "Thiếu Nhi"))
        list.add(TabItemModel(3, "Tâm Lý Kỹ - Năng Sống"))
        list.add(TabItemModel(4, "Văn Học"))
        listTemp = list
        viewPager?.offscreenPageLimit = 5
        viewPager?.adapter = adapter
        tabLayout?.setupWithViewPager(viewPager)
        list.forEachIndexed { index, viewModel ->
            tabLayout?.getTabAt(index)?.text = viewModel.name
        }
        return view
    }



    internal class ViewPagerAdapter(manager: FragmentManager) :
        FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val mFragmentList: SparseArray<Fragment> = SparseArray()

        override fun getItem(position: Int): Fragment {
            return ChildShowMoreDealFragment(listTemp[position].id)
        }

        override fun getCount(): Int {
            return listTemp.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val fragment = super.instantiateItem(container, position)
            mFragmentList.put(position, fragment as Fragment)
            return fragment
        }

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
            super.destroyItem(container, position, any)
            mFragmentList.remove(position)
        }
    }

}
