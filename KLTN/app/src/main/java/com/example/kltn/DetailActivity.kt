package com.example.kltn

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.kltn.screen.cart.CartFragment
import com.example.kltn.screen.cart.CartFragment.Companion.arrayListCart
import com.example.kltn.screen.cart.model.CartModel
import com.example.kltn.screen.home.model.DealsModel
import kotlinx.android.synthetic.main.custom_toolbar.*
import java.lang.Exception

class DetailActivity() : AppCompatActivity(), Parcelable {
    lateinit var dealModel: DealsModel
    lateinit var btnMoveCart: Button
    lateinit var btnAddProductToCart: Button
    var checkArrayListCart: ArrayList<CartModel> = ArrayList<CartModel>()


    constructor(parcel: Parcel) : this() {
        dealModel = parcel.readParcelable(DealsModel::class.java.classLoader)!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_detail_book)
        btnMoveCart = findViewById(R.id.btn_move_cart)
        btnAddProductToCart = findViewById(R.id.btn_add_product_to_cart)
        var checkClick:Int =0
        btnMoveCart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("check", 1)
            this.startActivity(intent)
        }
        setDialogFullScreen()
//        checkArrayListCart = arrayListCart
        val intent = getIntent()
        try {
            dealModel = intent.getParcelableExtra<DealsModel>("deal")
            Toast.makeText(this,dealModel.titleBookDeal,Toast.LENGTH_LONG).show()
            btnAddProductToCart.setOnClickListener {
                if(checkClick==0) {
                    try {
                        if (arrayListCart.isEmpty()) {
                            arrayListCart.add(
                                CartModel(
                                    dealModel.titleBookDeal,
                                    1,
                                    dealModel.price,
                                    R.drawable.vd1_sach
                                )
                            )
                        } else {
                            arrayListCart.forEach {
                                if (it.tenSach.compareTo(dealModel.titleBookDeal) == 0) {
                                    it.soLuong += 1
                                } else {
                                    arrayListCart.add(
                                        CartModel(
                                            dealModel.titleBookDeal,
                                            1,
                                            dealModel.price,
                                            R.drawable.vd1_sach
                                        )
                                    )
                                    Toast.makeText(this, "Thêm thành công", Toast.LENGTH_LONG)
                                        .show()
                                }
                            }
                        }
                    } catch (e: Exception) {

                    }
                }
                else
                {
                    Toast.makeText(this,"Sản phẩm đã được thêm vào giỏ hàng",Toast.LENGTH_LONG).show()
                }
                checkClick = 1
//                arrayListCart = checkArrayListCart
            }
        } catch (e: Exception) {

        }

        btn_back.setOnClickListener {
            onBackPressed()
        }
        btn_back_home.setOnClickListener {
            onBackPressed()
        }
        btn_back_cart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("check", 1)
            this.startActivity(intent)
        }

    }

    private fun setDialogFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            this.window?.statusBarColor = this.resources.getColor(R.color.colorPrimary)
            this.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<DetailActivity> {
        override fun createFromParcel(parcel: Parcel): DetailActivity {
            return DetailActivity(parcel)
        }

        override fun newArray(size: Int): Array<DetailActivity?> {
            return arrayOfNulls(size)
        }
    }

    fun reLoadFragment() {
        var frg: Fragment? = null
        frg = this.supportFragmentManager.findFragmentByTag("HomeFragment")
        val ft: FragmentTransaction = this.getSupportFragmentManager().beginTransaction()
        ft.detach(frg!!)
        ft.attach(frg!!)
        ft.commit()
    }
}
