package com.example.kltn.screen.profile

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.event.OnActionData
import com.example.kltn.screen.home.model.BookModel
import com.example.kltn.screen.profile.adapter.FavoriteAdapter
import com.example.kltn.screen.profile.adapter.InformationAdapter
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.reponse.DeleteFavoriteResponse
import com.example.kltn.screen.retrofit.reponse.FavoriteResponse
import com.example.kltn.screen.retrofit.reponse.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteFragment : Fragment() {
    lateinit var recyclerViewFavorite: RecyclerView
    lateinit var favoriteAdapter: FavoriteAdapter
    lateinit var btnBack_Favorite: ImageView
    lateinit var progressBarHolder: ProgressBar
    private var onActionData: OnActionData<BookModel>?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        recyclerViewFavorite = view.findViewById(R.id.recyclerview_favorite)
        progressBarHolder = view.findViewById(R.id.progressBarHolder)
        progressBarHolder.visibility = View.VISIBLE
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
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        var token = pref.getString("TokenLocal","")
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
                                "TL001",
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
                    onActionData = object : OnActionData<BookModel>
                    {
                        override fun onAction(data: BookModel) {
                            progressBarHolder.visibility = View.VISIBLE
                            deleteFavorite(data.maSach)
                        }

                    }
                    progressBarHolder.visibility = View.GONE
                    favoriteAdapter = FavoriteAdapter(activity!!, arrayListFavorite,onActionData!!)
                    recyclerViewFavorite.adapter = favoriteAdapter
                }
            }
        })

    }

    private fun deleteFavorite(maSach:String)
    {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        var token = pref.getString("TokenLocal", "")
        val maKH = pref.getString("MaKH","")
        val service =
            RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.deleteFavorite("Bearer " + token, maKH!!, maSach)
        call?.enqueue(object : Callback<DeleteFavoriteResponse> {
            override fun onFailure(call: Call<DeleteFavoriteResponse>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<DeleteFavoriteResponse>,
                response: Response<DeleteFavoriteResponse>
            ) {
                Toast.makeText(
                    context,
                    response.body()!!.message,
                    Toast.LENGTH_LONG
                ).show()
                progressBarHolder.visibility = View.GONE
                reLoadFragment()
            }
        })
    }
    fun reLoadFragment() {
        var frg: Fragment? = null
        frg =(context as FragmentActivity).getSupportFragmentManager().findFragmentByTag("FavoriteFragment")
        val ft: FragmentTransaction = (context as FragmentActivity).getSupportFragmentManager().beginTransaction()
        ft.detach(frg!!)
        ft.attach(frg!!)
        ft.commit()
    }
}