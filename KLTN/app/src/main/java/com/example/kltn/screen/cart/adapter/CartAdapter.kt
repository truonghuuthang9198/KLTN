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
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.MainActivity
import com.example.kltn.R
import com.example.kltn.screen.cart.CartFragment
import com.example.kltn.screen.cart.model.CartModel
import java.text.NumberFormat
import java.util.*


class CartAdapter internal constructor(var context: Context,var CartModel: ArrayList<CartModel>)
    : RecyclerView.Adapter<CartAdapter.CartViewHolder>(){

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

    override fun getItemCount() = CartModel.size
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {

        val current = CartModel[position]
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
            reLoadFragment()
            holder.soluong.text = soluong.toString()
//            val intent = Intent(context, MainActivity::class.java)
//            intent.putExtra("checkclick",1)
//            context.startActivity(intent)
            //this.notifyDataSetChanged()
        }
        holder.btnTru.setOnClickListener{
            soluong-=1
            current.soLuong = soluong
            reLoadFragment()
            //CartFragment.arrayListCart.get(position).soLuong = soluong
            holder.soluong.text = soluong.toString()
            //this.notifyDataSetChanged()
        }
        holder.btnDelete.setOnClickListener{
            CartModel.removeAt(position)
            reLoadFragment()
            this.notifyDataSetChanged()
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,CartModel.size)
        }
        holder.igmsach.setImageResource(current.image)
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