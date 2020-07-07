package com.example.kltn.screen.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.MainActivity
import com.example.kltn.R
import com.example.kltn.screen.home.adapter.MenuAdapter
import com.example.kltn.screen.home.bestbook.BestBookFragment
import com.example.kltn.screen.home.deals.DealFragment
import com.example.kltn.screen.home.model.MenuModel
import com.example.kltn.screen.home.sgk.SGKFragment
import com.example.kltn.screen.event.OnActionNotify

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    lateinit var recyclerviewMenu: RecyclerView
    lateinit var menuAdapter: MenuAdapter
    lateinit var btnCategory: ImageView
    private var onActionNotify: OnActionNotify? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        btnCategory = view.findViewById(R.id.img_danhmuc)
        btnCategory.setOnClickListener {
            loadFragmentCategory(CategoryFragment(),"CategoryFragment")
        }
        loadFragmentDeal(DealFragment())
        loadFragmentSGK(SGKFragment())
        loadFragmentBestBook(BestBookFragment())
        recyclerviewMenu = view!!.findViewById(R.id.recyclerview_menu)
        setUpRecyclerView()
        return view
    }

    private fun loadFragmentDeal(fragment: Fragment?): Boolean {
        if (fragment != null) {
            fragmentManager!!
                .beginTransaction()
                .replace(R.id.frame_deals, fragment)
                .commit()
            return true
        }
        return false
    }
    private fun loadFragmentSGK(fragment: Fragment?): Boolean {
        if (fragment != null) {
            fragmentManager!!
                .beginTransaction()
                .replace(R.id.frame_SGK, fragment)
                .commit()
            return true
        }
        return false
    }
    private fun loadFragmentBestBook(fragment: Fragment?): Boolean {
        if (fragment != null) {
            fragmentManager!!
                .beginTransaction()
                .replace(R.id.frame_bestBook, fragment)
                .commit()
            return true
        }
        return false
    }
    private fun loadFragmentCategory(fragment: Fragment?, tag: String): Boolean {
        if (fragment != null) {
            (context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment, tag)
                .addToBackStack(null)
                .commit()
            return true
        }
        return false
    }

    fun setUpRecyclerView()
    {
        recyclerviewMenu.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.HORIZONTAL,false)
        recyclerviewMenu.layoutManager = GridLayoutManager(activity,5)

        val arrayList = ArrayList<MenuModel>()
        arrayList.add(MenuModel(1,"Danh Mục",R.drawable.ic_danhmuc))
        arrayList.add(MenuModel(2,"Flash Sale",R.drawable.ic_flash))
        arrayList.add(MenuModel(3,"Deal Hot",R.drawable.ic_dealhot))
        arrayList.add(MenuModel(4,"Gợi Ý",R.drawable.ic_goiy))
        arrayList.add(MenuModel(5,"Đồ Chơi",R.drawable.ic_dochoi))
        arrayList.add(MenuModel(6,"Văn Phòng Phẩm",R.drawable.ic_vpp))
        arrayList.add(MenuModel(7,"Văn Học",R.drawable.ic_vanhoc))
        arrayList.add(MenuModel(8,"Thiếu Nhi",R.drawable.ic_thieunhi))
        arrayList.add(MenuModel(9," Tâm Lý Kỹ  Năng",R.drawable.ic_tlkn))
        arrayList.add(MenuModel(10,"Kinh tế",R.drawable.ic_kinhte))
        onActionNotify = object : OnActionNotify {
            override fun onActionNotify() {
//                  sendData?.ChangeStateSuggest()
//                val mainActivity = HomeFragment()
//                val bundle = Bundle()
//                bundle.putInt("setStateSuggest",1)
//                mainActivity.ar
            }
        }
        menuAdapter = MenuAdapter(context,arrayList,onActionNotify!!)
        recyclerviewMenu.adapter = menuAdapter
    }
}
