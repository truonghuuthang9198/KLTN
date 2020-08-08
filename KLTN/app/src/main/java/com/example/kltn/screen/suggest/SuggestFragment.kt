package com.example.kltn.screen.suggest


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.cart.CartFragment
import com.example.kltn.screen.cart.model.CartModel
import com.example.kltn.screen.cart.roomcart.CartViewModel
import com.example.kltn.screen.home.adapter.ShowMoreDealAdapter
import com.example.kltn.screen.home.model.BookModel
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.reponse.SachResponse
import com.example.kltn.screen.retrofit.reponse.SuggestResponse
import com.example.kltn.screen.retrofit.request.SuggestRequest
import com.example.kltn.screen.suggest.adapter.SuggestAdapter
import com.example.kltn.screen.suggest.model.SuggestModel
import com.example.kltn.screen.suggest.roomsuggest.SuggestViewModel
import com.google.common.eventbus.Subscribe
import de.greenrobot.event.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class SuggestFragment : Fragment() {
    lateinit var recyclerviewSuggest: RecyclerView
    lateinit var suggestAdapter: SuggestAdapter
    lateinit var suggestViewModel: SuggestViewModel
    lateinit var tv_null_suggest:TextView

    companion object {
        var listBook : ArrayList<BookModel> = ArrayList<BookModel>()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_suggest, container, false)
        recyclerviewSuggest = view.findViewById(R.id.recyclerview_suggest)
        recyclerviewSuggest.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
        tv_null_suggest = view.findViewById(R.id.tv_null_suggest)
        recyclerviewSuggest.layoutManager = GridLayoutManager(activity, 2)
        var arrayListSuggest = ArrayList<SuggestModel>()
        suggestViewModel = ViewModelProviders.of(this).get(SuggestViewModel::class.java)
        arrayListSuggest = suggestViewModel.getList() as ArrayList<SuggestModel>
        listBook = ArrayList<BookModel>()
//        val list : List<String> = arrayListSuggest as List<String>
        if(suggestViewModel.countKeySuggest()==2)
        {
            tv_null_suggest.visibility = View.GONE
            recyclerviewSuggest.visibility = View.VISIBLE
            val list = mutableListOf<String>()
            arrayListSuggest.forEach {
                list.add(it.maTL)
            }
            val suggestResquest = SuggestRequest(list)
            getListSuggest(suggestResquest)
        }
        else {
            tv_null_suggest.visibility = View.VISIBLE
            recyclerviewSuggest.visibility = View.GONE
        }

        Toast.makeText(
            activity!!, arrayListSuggest.toString(), Toast.LENGTH_LONG
        ).show()

        return view
    }
    fun getListSuggest(suggestResquest: SuggestRequest){
        val service =
            RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.getListSuggest(suggestResquest)
        call?.enqueue(object : Callback<List<SuggestResponse>> {
            override fun onFailure(call: Call<List<SuggestResponse>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<SuggestResponse>>,
                response: Response<List<SuggestResponse>>
            ) {
                response.body()!!.forEachIndexed { index, it ->
                    listBook.add(
                        BookModel(
                            index,
                            0,
                            it.ghiChu,
                            it.giaBan,
                            it.giamGia,
                            it.hinhAnh,
                            it.kichThuoc,
                            it.loaiBia,
                            it.tenCongTy,
                            it.maSach,
                            it.tenTacGia,
                            it.tenTheLoai,
                            it.maTheLoai,
                            it.ngayXuatBan.toString(),
                            it.tenNhaXuatBan,
                            it.soLuong,
                            it.soTrang,
                            it.tenSach,
                            it.tinhTrang,
                            it.soSao
                        )
                    )
                }
                suggestAdapter = SuggestAdapter(context,listBook)
                recyclerviewSuggest.adapter = suggestAdapter
            }
        })
    }
}
