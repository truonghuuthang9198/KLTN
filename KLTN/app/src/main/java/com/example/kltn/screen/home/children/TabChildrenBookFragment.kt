package com.example.kltn.screen.home.children

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.home.adapter.ChildrenBookAdapter
import com.example.kltn.screen.home.model.BookModel

class TabChildrenBookFragment(val tabId: Int) : Fragment() {
    lateinit var recycleviewChildrenBook: RecyclerView
    lateinit var bestbookAdapter: ChildrenBookAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tab_fragment_childrenbook, container, false)
        recycleviewChildrenBook = view!!.findViewById(R.id.recyclerview_childrenbook)
        setUpRecyclerView()
        return view
    }

    fun setUpRecyclerView() {
        recycleviewChildrenBook.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL, false
        )
        val arrayList = ArrayList<BookModel>()
        val listTab0 = ArrayList<BookModel>()

        arrayList.forEach {
            if (it.tabId == tabId) {
                listTab0.add(it)
            }
        }
        bestbookAdapter = ChildrenBookAdapter(listTab0)
        recycleviewChildrenBook.adapter = bestbookAdapter
    }

}