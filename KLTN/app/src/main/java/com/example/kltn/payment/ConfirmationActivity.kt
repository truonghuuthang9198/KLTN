package com.example.kltn.payment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.kltn.MainActivity
import com.example.kltn.R
import com.example.kltn.screen.cart.model.CartModel
import com.example.kltn.screen.cart.model.CheckBillModel
import com.example.kltn.screen.cart.roomcart.CartViewModel
import com.example.kltn.screen.firebase.ChiTietHDModel
import com.example.kltn.screen.firebase.HoaDonModel
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONException
import org.json.JSONObject
import java.time.LocalDate


class ConfirmationActivity : AppCompatActivity() {
    lateinit var textViewId: TextView
    lateinit var textViewStatus:TextView
    lateinit var textViewAmount: TextView
    lateinit var btn_tieptuc_muahang_confirm_pay: Button
    val arrayListCheckBill = ArrayList<CheckBillModel>()
    private lateinit var cartViewModel: CartViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)
        setDialogFullScreen()
        textViewId  = findViewById(R.id.paymentId)
        textViewStatus = findViewById(R.id.paymentStatus)
        textViewAmount= findViewById(R.id.paymentAmount)
        btn_tieptuc_muahang_confirm_pay = findViewById(R.id.btn_tieptuc_muahang_confirm_pay)
        btn_tieptuc_muahang_confirm_pay.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)

        }
        val intent = intent

        try {
            addBillToFireBase()
            val jsonDetails = JSONObject(intent.getStringExtra("PaymentDetails"))
                val rs = jsonDetails.getJSONObject("response")
                textViewId.append(rs.getString("id"))
                textViewStatus.append("Thành công")
                val paymentAmount = intent.getDoubleExtra("PaymentAmount",0.0)
                textViewAmount.append(paymentAmount.toString()+" $")
            cartViewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)
            cartViewModel.deleteAll()
        } catch (e: JSONException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun addBillToFireBase() {
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)
        val arrayListCart = cartViewModel.getList() as ArrayList<CartModel>
        arrayListCart.forEach {
            arrayListCheckBill.add(
                CheckBillModel(
                    it.maSach,
                    it.tenSach,
                    it.soLuong,
                    it.giaTien,
                    it.image
                )
            )
        }
        val intent = getIntent()
        var diachi = intent.getStringExtra("diachi")
        val arrayList = ArrayList<ChiTietHDModel>()
        var stt: Int = 1
        var tongtienHD: Double = 0.0
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        var makh = pref.getString("MaKH","")
        arrayListCheckBill.forEach {
            arrayList.add(ChiTietHDModel(it.maSach, it.soLuong, it.giaTien))
            tongtienHD += (it.soLuong * it.giaTien)
            stt++
        }
        val database = FirebaseDatabase.getInstance().getReference("Bills")
        val billId = database.push().key
        val hoaDon = HoaDonModel(
            "MaHD",
            makh.toString(),
            LocalDate.now().toString(),
            tongtienHD,
            diachi,
            arrayList
        )
        database.child(billId!!).setValue(hoaDon).addOnCompleteListener {
        }
    }
}