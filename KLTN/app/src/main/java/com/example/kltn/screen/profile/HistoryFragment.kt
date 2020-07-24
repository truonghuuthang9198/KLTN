package com.example.kltn.screen.profile
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.home.model.BookModel
import com.example.kltn.screen.profile.adapter.FavoriteAdapter
import com.example.kltn.screen.profile.adapter.HistoryBillAdapter
import com.example.kltn.screen.profile.model.FavoriteModel
import com.example.kltn.screen.profile.model.HistoryBillModel
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.reponse.FavoriteResponse
import com.example.kltn.screen.retrofit.reponse.HistoryResponse
import kotlinx.android.synthetic.main.fragment_information_ship.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("DEPRECATION")
class HistoryFragment : Fragment() {
    lateinit var recyclerview_bill: RecyclerView
    lateinit var adapterHistoryBill: HistoryBillAdapter
    lateinit var btn_back_history_muahang: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history_sale_book, container, false)
        recyclerview_bill = view.findViewById(R.id.recyclerview_history_bill)
        recyclerview_bill.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        btn_back_history_muahang = view.findViewById(R.id.btn_back_history_muahang)
        btn_back_history_muahang.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }
        setUpRecyclerview()
        return view
    }
    fun setUpRecyclerview()
    {
        val arrayList = ArrayList<HistoryBillModel>()
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        var token = pref.getString("TokenLocal","")
        val service = RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.getListHistory("Bearer "+token)
        call?.enqueue(object : Callback<List<HistoryResponse>> {
            override fun onFailure(call: Call<List<HistoryResponse>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }
            override fun onResponse(
                call: Call<List<HistoryResponse>>,
                response: Response<List<HistoryResponse>>
            ) {
                if ( response.isSuccessful) {
                    response.body()!!.forEach {
                        arrayList.add(HistoryBillModel(it.maHoaDon,it.ngayLap,it.thanhTien))
                    }
                    adapterHistoryBill = HistoryBillAdapter(context,arrayList,response!!)
                    recyclerview_bill.adapter = adapterHistoryBill
                }
            }
        })
    }
}