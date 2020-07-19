package com.example.kltn.screen.profile

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.home.model.BookModel
import com.example.kltn.screen.profile.adapter.FavoriteAdapter
import com.example.kltn.screen.profile.adapter.InformationAdapter
import com.example.kltn.screen.profile.model.FavoriteModel
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.reponse.FavoriteResponse
import com.example.kltn.screen.retrofit.reponse.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteFragment : Fragment() {
    lateinit var recyclerViewFavorite: RecyclerView
    lateinit var favoriteAdapter: FavoriteAdapter
    lateinit var btnBack_Favorite: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        recyclerViewFavorite = view.findViewById(R.id.recyclerview_favorite)
        recyclerViewFavorite.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        addList()
        btnBack_Favorite = view.findViewById(R.id.btn_back_favorite)
        btnBack_Favorite.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }
        return view
    }

    fun addList() {
        var arrayListFavorite: ArrayList<BookModel> = ArrayList<BookModel>()
//        arrayListFavorite.add(
//            FavoriteModel(
//                R.drawable.vd3_sach,
//                "The fellowship of the Ring (The Lord of The Rings,Book1)",
//                26000.00
//            )
//        )
//        arrayListFavorite.add(
//            FavoriteModel(
//                R.drawable.vd3_sach,
//                "The fellowship of the Ring (The Lord of The Rings,Book1)",
//                26000.00
//            )
//        )
//        arrayListFavorite.add(
//            FavoriteModel(
//                R.drawable.vd3_sach,
//                "The fellowship of the Ring (The Lord of The Rings,Book1)",
//                26000.00
//            )
//        )
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        var token = pref.getString("Token","")
        val service = RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.getListFavorite("Bearer "+token)
        call?.enqueue(object : Callback<List<FavoriteResponse>> {
            override fun onFailure(call: Call<List<FavoriteResponse>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }
            override fun onResponse(
                call: Call<List<FavoriteResponse>>,
                response: Response<List<FavoriteResponse>>
            ) {
                if ( response.isSuccessful) {
                    response.body()?.forEach {
                        arrayListFavorite.add(
                            BookModel(
                                0,
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
                                it.ngayXuatBan,
                                it.tenNhaXuatBan,
                                it.soLuong,
                                it.soTrang,
                                it.tenSach,
                                it.tinhTrang,
                                it.soSao
                            )
                        )
                    }
                    favoriteAdapter = FavoriteAdapter(activity!!, arrayListFavorite)
                    recyclerViewFavorite.adapter = favoriteAdapter
                }
            }
        })

    }
}