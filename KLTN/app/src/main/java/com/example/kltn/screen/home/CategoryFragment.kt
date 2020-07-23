package com.example.kltn.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.home.adapter.CategoryAdapter
import com.example.kltn.screen.home.adapter.CategoryDetailAdapter
import com.example.kltn.screen.home.model.CategoryDetailModel
import com.example.kltn.screen.home.model.CategoryModel
import com.example.kltn.screen.event.OnActionData

class CategoryFragment : Fragment() {
    lateinit var recyclerViewTopic: RecyclerView
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var backButtonCategory: ImageView
    lateinit var recyclerViewCategoryDetail: RecyclerView
    lateinit var categoryDetailAdapter: CategoryDetailAdapter
    private var onActionData: OnActionData<CategoryModel>? = null

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
        backButtonCategory = view.findViewById(R.id.btn_back_category)
        backButtonCategory.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }
        setUpRecyclerview()
        return view
    }


    fun setUpRecyclerview() {
        val arrayListSachTN = ArrayList<CategoryDetailModel>()
        arrayListSachTN.add(CategoryDetailModel("Tất cả sản phẩm"))
        arrayListSachTN.add(CategoryDetailModel("Văn Học"))
        arrayListSachTN.add(CategoryDetailModel("Kinh Tế"))
        arrayListSachTN.add(CategoryDetailModel("Tâm Lý - Kĩ Năng Sống"))
        arrayListSachTN.add(CategoryDetailModel("Sách Thiếu Nhi"))
        arrayListSachTN.add(CategoryDetailModel("Tiểu Sử - Hồi Ký"))
        if (arrayListCategory.isEmpty()) {
            arrayListCategory.add(CategoryModel(0, "CT001","Sách Trong Nước",true))
            arrayListCategory.add(CategoryModel(1, "CT002","FOREIGN BOOKS"))
            arrayListCategory.add(CategoryModel(2, "CT003","VPP - Dụng Cụ Học Sinh"))
            arrayListCategory.add(CategoryModel(3, "CT004","Tuyển Tập"))
            arrayListCategory.add(CategoryModel(4, "CT005","Sách Theo Nhà Cung Cấp"))
            arrayListCategory.add(CategoryModel(5, "CT006","Khuyên Đọc"))
        }
        categoryDetailAdapter = CategoryDetailAdapter(context, arrayListSachTN,arrayListCategory[0].maCategory)
        recyclerViewCategoryDetail.adapter = categoryDetailAdapter
        onActionData = object : OnActionData<CategoryModel> {
            override fun onAction(data: CategoryModel) {
                when (data.id) {
                    0 -> {
                        categoryDetailAdapter = CategoryDetailAdapter(activity!!, arrayListSachTN,data.maCategory)
                        recyclerViewCategoryDetail.adapter = categoryDetailAdapter
                    }
                    2 -> {

                    }

                }
            }
        }
        categoryAdapter = CategoryAdapter(context, arrayListCategory, onActionData!!)
        recyclerViewTopic.adapter = categoryAdapter
    }


}