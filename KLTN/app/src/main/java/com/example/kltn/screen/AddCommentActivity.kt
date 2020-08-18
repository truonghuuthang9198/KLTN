package com.example.kltn.screen

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import android.widget.RatingBar.OnRatingBarChangeListener
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.kltn.MainActivity
import com.example.kltn.R
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.reponse.CommentResponse
import com.example.kltn.screen.retrofit.reponse.SuggestResponse
import com.example.kltn.screen.retrofit.request.CommentRequest
import com.example.kltn.screen.retrofit.request.SuggestRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddCommentActivity() : AppCompatActivity() {
    lateinit var rating_comment: RatingBar
    lateinit var edt_hoTen: EditText
    lateinit var edt_tuaDe: EditText
    lateinit var edt_nhanxet: EditText
    lateinit var btn_add_comment: Button
    lateinit var btn_back_activity: ImageView
    private var sosao:Int = 0
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_comment)
        val intent = getIntent()
        rating_comment = findViewById(R.id.rating_comment)
        rating_comment.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Toast.makeText(this, rating.toString(), Toast.LENGTH_LONG).show()
            sosao = rating.toInt()
        }
        edt_hoTen = findViewById(R.id.edt_hoTen)
        edt_tuaDe = findViewById(R.id.edt_tuaDe)
        btn_back_activity = findViewById(R.id.btn_back_activity)
        btn_back_activity.setOnClickListener {
            onBackPressed()
        }
        edt_nhanxet = findViewById(R.id.edt_nhanxet)
        btn_add_comment = findViewById(R.id.btn_add_comment)
        val masach = intent.getStringExtra("maSach")
        Toast.makeText(this,masach,Toast.LENGTH_LONG).show()
        btn_add_comment.setOnClickListener {
            val commentRequest = CommentRequest(masach,edt_hoTen.text.toString(),sosao,edt_nhanxet.text.toString(),java.time.LocalDate.now().toString())
            addComment(commentRequest)
            onBackPressed()
        }
        setDialogFullScreen()
    }

    private fun setDialogFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            this.window?.statusBarColor = this.resources.getColor(R.color.colorPrimary)
            this.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    fun addComment(commentRequest: CommentRequest) {
        val service =
            RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.addComment(commentRequest)
        call?.enqueue(object : Callback<CommentResponse> {
            override fun onFailure(call: Call<CommentResponse>, t: Throwable) {

            }
            override fun onResponse(
                call: Call<CommentResponse>,
                response: Response<CommentResponse>
            ) {
                if(response.isSuccessful) {
                    Toast.makeText(this@AddCommentActivity, "Cảm ơn bạn đã đóng góp nhận xét", Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }
}