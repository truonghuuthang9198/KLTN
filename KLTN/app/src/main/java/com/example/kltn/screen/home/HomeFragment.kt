package com.example.kltn.screen.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.kltn.R
import com.example.kltn.SearchActivity
import com.example.kltn.screen.event.OnActionNotify
import com.example.kltn.screen.home.adapter.MenuAdapter
import com.example.kltn.screen.home.toys.ToysFragment
import com.example.kltn.screen.home.children.ChildrenBookFragment
import com.example.kltn.screen.home.deal.DealFragment
import com.example.kltn.screen.home.economic.EconomicFragment
import com.example.kltn.screen.home.literary.LiteraryFragment
import com.example.kltn.screen.home.model.MenuModel
import com.example.kltn.screen.home.psychological_skills.PsychologicalSkillsFragment
import com.example.kltn.screen.home.stationery.StationeryFragment
import com.example.kltn.screen.profile.model.SendArrayAddress
import com.facebook.FacebookSdk.getApplicationContext
import de.greenrobot.event.EventBus


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

    lateinit var recyclerviewMenu: RecyclerView
    lateinit var menuAdapter: MenuAdapter
    lateinit var btnCategory: ImageView
    lateinit var editSearchView: SearchView
    lateinit var viewpager: ViewPager
    private var onActionNotify: OnActionNotify? = null
    lateinit var slideDots:LinearLayout
    private var dotsCount:Int = 0
    private lateinit var dots: Array<ImageView?>

    var sendData: SendData? = null

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            sendData = activity as SendData
        } catch (e: ClassCastException) {
            throw ClassCastException("$activity must implement onSomeEventListener")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        btnCategory = view.findViewById(R.id.img_danhmuc)
        editSearchView = view.findViewById(R.id.search_view_home)
        slideDots = view.findViewById(R.id.slideDots)
        viewpager = view.findViewById(R.id.design_potter)
        editSearchView.setOnQueryTextListener(this)



        closeKeyboardFromFragment()

        btnCategory.setOnClickListener {
            loadFragmentCategory(CategoryFragment(), "CategoryFragment")
        }
//        loadFragmentDeal(DealFragment())
        loadFragmentLiterary(LiteraryFragment())
        loadFragmentBestBook(ToysFragment())
        loadFragmentChildrenBook(ChildrenBookFragment())
        loadFragmentEconomic(EconomicFragment())
        loadFragmentStationery(StationeryFragment())
        loadFragmentPsychologicalSkill(PsychologicalSkillsFragment())
        recyclerviewMenu = view!!.findViewById(R.id.recyclerview_menu)
        setUpRecyclerView()
        imageSliderImplementation()
        return view
    }

    private fun loadFragmentStationery(fragment: Fragment?): Boolean {
        if (fragment != null) {
            fragmentManager!!
                .beginTransaction()
                .replace(R.id.frame_stationery, fragment)
                .commit()
            return true
        }
        return false
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

    private fun loadFragmentEconomic(fragment: Fragment?): Boolean {
        if (fragment != null) {
            fragmentManager!!
                .beginTransaction()
                .replace(R.id.frame_economic, fragment)
                .commit()
            return true
        }
        return false
    }

    private fun loadFragmentPsychologicalSkill(fragment: Fragment?): Boolean {
        if (fragment != null) {
            fragmentManager!!
                .beginTransaction()
                .replace(R.id.frame_psychologicalSkills, fragment)
                .commit()
            return true
        }
        return false
    }

    private fun loadFragmentLiterary(fragment: Fragment?): Boolean {
        if (fragment != null) {
            fragmentManager!!
                .beginTransaction()
                .replace(R.id.frame_literary, fragment)
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

    private fun loadFragmentChildrenBook(fragment: Fragment?): Boolean {
        if (fragment != null) {
            fragmentManager!!
                .beginTransaction()
                .replace(R.id.frame_childrenBook, fragment)
                .commit()
            return true
        }
        return false
    }

    private fun imageSliderImplementation() {
        val adapter = SlideAdapter(context)
        viewpager.adapter = adapter
        dotsCount = adapter.count
        dots = arrayOfNulls(dotsCount)

        for (i in 0 until dotsCount) {
            dots[i] = ImageView(context)
            dots[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.nonactive_dot
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            slideDots.addView(dots[i], params)
        }


        dots[0]?.setImageDrawable(
            ContextCompat.getDrawable(
                getApplicationContext(),
                R.drawable.active_dot
            )
        )

        viewpager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if(dotsCount>6)
                    dotsCount = 0
                for (i in 0 until dotsCount) {
                    dots[i]?.setImageDrawable(
                        ContextCompat.getDrawable(
                            getApplicationContext(),
                            R.drawable.nonactive_dot
                        )
                    )
                }
                dots[position]?.setImageDrawable(
                    ContextCompat.getDrawable(
                        getApplicationContext(),
                        R.drawable.active_dot
                    )
                )
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
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

    fun setUpRecyclerView() {
        recyclerviewMenu.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL, false
        )
        recyclerviewMenu.layoutManager = GridLayoutManager(activity, 5)

        val arrayList = ArrayList<MenuModel>()
        arrayList.add(MenuModel(1, "Danh Mục", R.drawable.ic_danhmuc))
        arrayList.add(MenuModel(2, "Flash Sale", R.drawable.ic_flash))
        arrayList.add(MenuModel(3, "Deal Hot", R.drawable.ic_dealhot))
        arrayList.add(MenuModel(4, "Gợi Ý", R.drawable.ic_goiy))
        arrayList.add(MenuModel(5, "Đồ Chơi", R.drawable.ic_dochoi))
        arrayList.add(MenuModel(6, "Văn Phòng Phẩm", R.drawable.ic_vpp))
        arrayList.add(MenuModel(7, "Văn Học", R.drawable.ic_vanhoc))
        arrayList.add(MenuModel(8, "Thiếu Nhi", R.drawable.ic_thieunhi))
        arrayList.add(MenuModel(9, " Tâm Lý Kỹ  Năng", R.drawable.ic_tlkn))
        arrayList.add(MenuModel(10, "Kinh tế", R.drawable.ic_kinhte))
        onActionNotify = object : OnActionNotify {
            override fun onActionNotify() {
                sendData?.ChangeStateBottomNavigation(1)
//                  sendData?.ChangeStateSuggest()
//                val mainActivity = HomeFragment()
//                val bundle = Bundle()
//                bundle.putInt("setStateSuggest",1)
//                mainActivity.ar
            }
        }
        menuAdapter = MenuAdapter(context, arrayList, onActionNotify!!)
        recyclerviewMenu.adapter = menuAdapter
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        val intent = Intent(context, SearchActivity::class.java)
        intent.putExtra("keySearch", query)
        context?.startActivity(intent)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onResume() {
        super.onResume()
    }

    fun closeKeyboardFromFragment() {
        val view = activity?.currentFocus
        if(view !=null) {
            val imm: InputMethodManager? =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }
    }
}
