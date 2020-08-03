package com.example.kltn.screen

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import android.widget.RatingBar.OnRatingBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.kltn.R


class AddCommentActivity() : AppCompatActivity() {
    lateinit var rating_comment: RatingBar
    lateinit var edt_hoTen:EditText
    lateinit var edt_tuaDe:EditText
    lateinit var edt_nhanxet:EditText
    lateinit var btn_add_comment:Button
    lateinit var btn_back_activity:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_comment)
        val intent = getIntent()
        rating_comment = findViewById(R.id.rating_comment)
        rating_comment.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Toast.makeText(this,rating.toString(),Toast.LENGTH_LONG).show()
        }
        edt_hoTen =findViewById(R.id.edt_hoTen)
        edt_tuaDe = findViewById(R.id.edt_tuaDe)
        btn_back_activity = findViewById(R.id.btn_back_activity)
        btn_back_activity.setOnClickListener {
            onBackPressed()
        }
        edt_nhanxet = findViewById(R.id.edt_nhanxet)
        btn_add_comment = findViewById(R.id.btn_add_comment)
        setDialogFullScreen()
        Toast.makeText(this,intent.getStringExtra("maSach"),Toast.LENGTH_LONG).show()
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