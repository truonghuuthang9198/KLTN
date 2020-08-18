package com.example.kltn.screen.notification

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.notification.adapter.NotificationAdapter
import com.example.kltn.screen.notification.model.NotificationModel
import com.example.kltn.screen.profile.InformationFragment
import com.example.kltn.screen.profile.ProfileFragment
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.reponse.CheckLoginResponse
import com.example.kltn.screen.retrofit.reponse.LoginResponse
import com.example.kltn.screen.retrofit.reponse.NotificationResponse
import kotlinx.android.synthetic.main.fragment_notification.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    fun setUpRecyclerView() {
        recycleviewNotification.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        var token = pref.getString("TokenLocal","")
        if (token!="") {
            val arrayList = ArrayList<NotificationModel>()
            val service =
                RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
            val call = service?.getListNotification("Bearer " + token)
            call?.enqueue(object : Callback<List<NotificationResponse>> {
                override fun onFailure(call: Call<List<NotificationResponse>>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<List<NotificationResponse>>,
                    response: Response<List<NotificationResponse>>
                ) {
                    response.body()!!.forEach {
                        arrayList.add(
                            NotificationModel(
                                it.tenThongBao,
                                it.noiDung,
                                it.ngayThongBao
                            )
                        )
                    }
                    notificationAdapter = NotificationAdapter(context, arrayList)
                    recycleviewNotification.adapter = notificationAdapter
                }
            })
        }
        else
        {
            Toast.makeText(context,"Hãy đăng nhập để FAHASA có thể gửi thông báo cho bạn",Toast.LENGTH_LONG).show()
        }

    }

}
