package com.example.kltn.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.home.adapter.CategoryAdapter
import com.example.kltn.screen.home.adapter.CategoryDetailAdapter
import com.example.kltn.screen.home.model.CategoryDetailModel
import com.example.kltn.screen.home.model.CategoryModel
import com.example.kltn.screen.event.OnActionData
import com.example.kltn.screen.home.toys.ShowMoreBestBookFragment
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.reponse.CategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryFragment : Fragment() {
    lateinit var recyclerViewTopic: RecyclerView
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var backButtonCategory: ImageView
    lateinit var recyclerViewCategoryDetail: RecyclerView
    lateinit var categoryDetailAdapter: CategoryDetailAdapter
    lateinit var progressBarHolder: ProgressBar
    private var onActionData: OnActionData<CategoryModel>? = null
    private var onActionDataDetailCategory: OnActionData<CategoryDetailModel>? = null

    companion object {
        var arrayListCategory: ArrayList<CategoryModel> = ArrayList<CategoryModel>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)
        recyclerViewTopic = view.findViewById(R.id.recyclerview_category_topic)
        recyclerViewCategoryDetail = view.findViewById(R.id.recyclerview_category_detail)
        recyclerViewCategoryDetail.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerViewTopic.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        progressBarHolder = view.findViewById(R.id.progressBarHolder)
        backButtonCategory = view.findViewById(R.id.btn_back_category)
        backButtonCategory.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }

        arrayListCategory.forEach {
            it.choose = false
        }
        setUpRecyclerview()
        return view
    }


    fun setUpRecyclerview() {
        progressBarHolder.visibility = View.VISIBLE
        val service =
            RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.getListCategory()
        call?.enqueue(object : Callback<List<CategoryResponse>> {
            override fun onFailure(call: Call<List<CategoryResponse>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<CategoryResponse>>,
                response: Response<List<CategoryResponse>>
            ) {
                if (arrayListCategory.isEmpty()) {
                    response.body()!!.forEachIndexed { index, categoryResponse ->
                        arrayListCategory.add(
                            CategoryModel(
                                index,
                                categoryResponse.maDanhMuc,
                                categoryResponse.tenDanhMuc
                            )
                        )
                    }

                }


                onActionData = object : OnActionData<CategoryModel> {
                    override fun onAction(dataCT: CategoryModel) {
                        response.body()!!.forEach {
                            if (it.maDanhMuc == dataCT.maCategory) {
                                val arrayListSachTN = ArrayList<CategoryDetailModel>()
                                it.theLoais.forEach {
                                    arrayListSachTN.add(
                                        CategoryDetailModel(
                                            it.maTheLoai,
                                            it.tenTheLoai
                                        )
                                    )
                                }
                                onActionDataDetailCategory =
                                    object : OnActionData<CategoryDetailModel> {
                                        override fun onAction(dataDCT: CategoryDetailModel) {
                                            loadFragment(ShowMoreBestBookFragment(dataCT.maCategory,dataDCT.maTheLoai),"ShowMoreBestBookFragment")
                                        }
                                    }
                                categoryDetailAdapter =
                                    CategoryDetailAdapter(
                                        context,
                                        arrayListSachTN,
                                        onActionDataDetailCategory!!
                                    )
                                recyclerViewCategoryDetail.adapter = categoryDetailAdapter
                            }
                        }
                    }
                }
                progressBarHolder.visibility = View.GONE
                categoryAdapter = CategoryAdapter(context, arrayListCategory, onActionData!!)
                recyclerViewTopic.adapter = categoryAdapter
            }
        })
    }
    private fun loadFragment(fragment: Fragment?, tag: String): Boolean {
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
}