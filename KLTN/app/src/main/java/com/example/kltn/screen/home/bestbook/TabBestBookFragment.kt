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
import com.example.kltn.screen.home.model.BookModel

class TabBestBookFragment(val tabId: Int) : Fragment() {
    lateinit var recycleviewBestBook: RecyclerView
    lateinit var bestbookAdapter: BestBookAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tab_fragment_bestbook, container, false)
        recycleviewBestBook = view!!.findViewById<RecyclerView>(R.id.recyclerview_bestbook)
        setUpRecyclerView()
        return view
    }

    fun setUpRecyclerView() {
        recycleviewBestBook.layoutManager = LinearLayoutManager(
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
        bestbookAdapter = BestBookAdapter(listTab0)
        recycleviewBestBook.adapter = bestbookAdapter
    }

}