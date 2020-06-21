package com.example.kltn.screen.home.bestbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.home.adapter.BestBookAdapter
import com.example.kltn.screen.home.adapter.SGKAdapter
import com.example.kltn.screen.home.model.BestBookModel
import com.example.kltn.screen.home.model.SGKModel

class ChildBestBookFragment(val tabId: Int) : Fragment() {
    lateinit var recycleviewBestBook: RecyclerView
    lateinit var bestbookAdapter: BestBookAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_child_bestbook, container, false)
        recycleviewBestBook = view!!.findViewById<RecyclerView>(R.id.recyclerview_bestbook)
        setUpRecyclerView()
        return view
    }
    fun setUpRecyclerView()
    {
        recycleviewBestBook.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.HORIZONTAL,false)
        val arrayList = ArrayList<BestBookModel>()
        val listTab0 = ArrayList<BestBookModel>()

        arrayList.add(
            BestBookModel(0,0, R.drawable.vd3_sach,"Dạy Trẻ Biết Đọc Sớm",53000.00,79000.00)
        )
        arrayList.add(
            BestBookModel(1,0, R.drawable.vd3_sach,"Get Set Go: Mathematics Equals",36400.00,52000.00)
        )
        arrayList.add(
            BestBookModel(2,0, R.drawable.vd3_sach,"Bí Mật Hành Trình Tình Yêu",30400.00,48000.00)
        )
        arrayList.add(
            BestBookModel(3,0, R.drawable.vd3_sach,"Dạy Tiếng Anh Xu Hướng Mới",33330.00,56000.00)
        )
        arrayList.add(
            BestBookModel(0,1, R.drawable.vd3_sach,"Dạy Trẻ Biết Đọc Sớm",53720.00,79000.00)
        )
        arrayList.add(
            BestBookModel(1,1, R.drawable.vd3_sach,"Get Set Go: Mathematics Equals",36000.00,52000.00)
        )
        arrayList.add(
            BestBookModel(2,1, R.drawable.vd3_sach,"Bí Mật Hành Trình Tình Yêu",30400.00,48000.00)
        )
        arrayList.add(
            BestBookModel(3,1, R.drawable.vd3_sach,"Dạy Tiếng Anh Xu Hướng Mới",33400.00,56000.00)
        )
        arrayList.add(
            BestBookModel(0,2, R.drawable.vd3_sach,"Dạy Trẻ Biết Đọc Sớm",53720.00,79000.00)
        )
        arrayList.add(
            BestBookModel(1,2, R.drawable.vd3_sach,"Get Set Go: Mathematics Equals",36000.00,52000.00)
        )
        arrayList.add(
            BestBookModel(2,2, R.drawable.vd3_sach,"Bí Mật Hành Trình Tình Yêu",30400.00,48000.00)
        )
        arrayList.add(
            BestBookModel(3,2, R.drawable.vd3_sach,"Dạy Tiếng Anh Xu Hướng Mới",33400.00,56000.00)
        )
        arrayList.forEach{
            if(it.tabId == tabId)
            {
                listTab0.add(it)
            }
        }
        bestbookAdapter = BestBookAdapter(listTab0)
        recycleviewBestBook.adapter = bestbookAdapter

    }

}