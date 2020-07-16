package com.example.kltn.screen.cart.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.MainActivity
import com.example.kltn.R
import com.example.kltn.screen.cart.CartFragment
import com.example.kltn.screen.cart.model.CartModel
import com.example.kltn.screen.cart.roomdatabase.CartViewModel
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class CartAdapter internal constructor(var context: Context?,var listCart: ArrayList<CartModel>)
    : RecyclerView.Adapter<CartAdapter.CartViewHolder>(){
    private lateinit var cartViewModel: CartViewModel
    companion object {
        var thanhtien:Double =0.0
    }
    inner class CartViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val tensach: TextView = itemView.findViewById(R.id.tv_tensach)
        val soluong: TextView = itemView.findViewById(R.id.tv_soluong)
        val giatien: TextView = itemView.findViewById(R.id.tv_giatien)
        val igmsach: ImageView = itemView.findViewById(R.id.img_sach)
        val btnCong: AppCompatImageView = itemView.findViewById(R.id.imgbutton_cong)
        val btnTru: AppCompatImageView = itemView.findViewById(R.id.imgbutton_tru)
        val btnDelete: ImageView = itemView.findViewById(R.id.imgbutton_delete)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recycleview_cart_item,parent,false)
        val CartViewHolder = CartViewHolder(cellForRow)
        return CartViewHolder
    }

    override fun getItemCount() = listCart.size
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        cartViewModel = ViewModelProviders.of(context as FragmentActivity).get(CartViewModel::class.java)
        val current = listCart[position]
        var soluong = current.soLuong
        holder.tensach.text = current.tenSach
        holder.soluong.text = current.soLuong.toString()
        val localVN = Locale("vi","VN")
        val numberFormat = NumberFormat.getCurrencyInstance(localVN)
        val giatienfm =numberFormat.format(current.giaTien)
        holder.giatien.text = giatienfm
        holder.btnCong.setOnClickListener {
            soluong+=1
            current.soLuong = soluong
            cartViewModel.updateSL(current.maSach,soluong)
            reLoadFragment()
            holder.soluong.text = soluong.toString()
        }
        holder.btnTru.setOnClickListener{
            soluong-=1
            current.soLuong = soluong
            cartViewModel.updateSL(current.maSach,soluong)
            reLoadFragment()
            holder.soluong.text = soluong.toString()
        }
        holder.btnDelete.setOnClickListener{
            cartViewModel.deleteItemCart(current)
            this.notifyDataSetChanged()
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,listCart.size)
            reLoadFragment()
        }
        Picasso.get().load(current.image).into(holder.igmsach)
    }
    fun updateThanhTien()
    {
        var thanhtienitemt:Double = 0.0
        var tongtien:Double = 0.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            CartFragment.arrayListCart.forEach {
                thanhtienitemt = (it.giaTien * it.soLuong)
                tongtien+= thanhtienitemt
            }
        }
        CartFragment.tongtien = tongtien
    }
    fun reLoadFragment() {
        var frg: Fragment? = null
        frg =(context as FragmentActivity).getSupportFragmentManager().findFragmentByTag("Cart")
        val ft: FragmentTransaction = (context as FragmentActivity).getSupportFragmentManager().beginTransaction()
        ft.detach(frg!!)
        ft.attach(frg!!)
        ft.commit()
    }
}