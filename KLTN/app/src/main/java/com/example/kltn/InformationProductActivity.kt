package com.example.kltn

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.example.kltn.screen.home.model.BookModel
import kotlinx.android.synthetic.main.activity_information_product.*

class InformationProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_product)
        setDialogFullScreen()
        val intent = getIntent()
        val book = intent.getParcelableExtra<BookModel>("InformationBook")
        tvSKU.text = book.maSach
        tvCtyPhatHanh.text = book.maCongTy
        tvAuthor.text = book.maTacGia
        tvNhaXuatBan.text = book.maNhaXuatBan
        tvKichThuoc.text = book.kichThuoc
        tvSoTrang.text = book.soTrang
        tvTheLoai.text = book.maTheLoai
        tvGhiChu.text = book.ghiChu
        btn_back_information_book.setOnClickListener {
            onBackPressed()
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
}