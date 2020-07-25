package com.example.kltn.screen.profile.adapter

import android.content.Context
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.FormatData
import com.example.kltn.screen.event.EventFireUtil
import com.example.kltn.screen.event.OnActionData
import com.example.kltn.screen.home.model.BookModel
import com.example.kltn.screen.profile.model.FavoriteModel
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.reponse.DeleteFavoriteResponse
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class FavoriteAdapter internal constructor(var context: Context?, var favoriteModel: ArrayList<BookModel>) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageBook_favorite: ImageView = itemView.findViewById(R.id.img_book_favorite)
        val titleBook_favorite: TextView = itemView.findViewById(R.id.title_book_favorite)
        val priceBook_favorite: TextView = itemView.findViewById(R.id.price_book_favorite)
        val btnMuaNgay: Button = itemView.findViewById(R.id.btn_muangay_favorite)
        val btnDelete: ImageView = itemView.findViewById(R.id.btn_delete_favorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_favorite, parent, false)
        val FavoriteViewHolder = FavoriteViewHolder(cellForRow)
        return FavoriteViewHolder
    }

    override fun getItemCount() = favoriteModel.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val current = favoriteModel[position]
        holder.titleBook_favorite.text = current.tenSach
        holder.priceBook_favorite.text = FormatData.formatMoneyVND(current.giaGiamDS)
        Picasso.get().load(current.hinhAnh).into(holder.imageBook_favorite)
        holder.btnDelete.setOnClickListener {
            deleteFavorite(current.maSach)
        }
        holder.btnMuaNgay.setOnClickListener{

        }
    }
    fun reLoadFragment() {
        var frg: Fragment? = null
        frg =(context as FragmentActivity).getSupportFragmentManager().findFragmentByTag("FavoriteFragment")
        val ft: FragmentTransaction = (context as FragmentActivity).getSupportFragmentManager().beginTransaction()
        ft.detach(frg!!)
        ft.attach(frg!!)
        ft.commit()
    }

    private fun loadFragment(fragment: Fragment?, tag: String): Boolean {
        if (fragment != null) {
            (context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment, tag)
                .addToBackStack(null)
                .commit()
            return true
        }
        return false
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
                reLoadFragment()
            }
        })
    }
}