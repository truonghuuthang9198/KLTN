package com.example.kltn.screen.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.notification.adapter.NotificationAdapter
import com.example.kltn.screen.notification.model.NotificationModel
import kotlinx.android.synthetic.main.fragment_notification.*

/**
 * A simple [Fragment] subclass.
 */
class NotificationFragment : Fragment() {
    lateinit var recycleviewNotification: RecyclerView
    lateinit var notificationAdapter: NotificationAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notification, container, false)
        recycleviewNotification = view!!.findViewById<RecyclerView>(R.id.recycleview_notification)
        setUpRecyclerView()
        return view
    }
    fun setUpRecyclerView()
    {
        recycleviewNotification.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL,false)
        val arrayList = ArrayList<NotificationModel>()
        arrayList.add(
            NotificationModel(
                R.drawable.vd3_sach,"Truong Huu Thang"
            )
        )
        notificationAdapter = NotificationAdapter(context,arrayList)
        recycleviewNotification.adapter = notificationAdapter
    }

}
