package com.example.kltn.screen

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.reponse.ReviewResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowCommentActivity() : AppCompatActivity() {
    lateinit var progressBar_5star: ProgressBar
    lateinit var progressBar_4star: ProgressBar
    lateinit var progressBar_3star: ProgressBar
    lateinit var progressBar_2star: ProgressBar
    lateinit var progressBar_1star: ProgressBar
    lateinit var rating_comment: RatingBar
    lateinit var tvSumStar: TextView
    lateinit var tvNumberReview: TextView
    lateinit var phantram_progress_5star: TextView
    lateinit var phantram_progress_4star: TextView
    lateinit var phantram_progress_3star: TextView
    lateinit var phantram_progress_2star: TextView
    lateinit var phantram_progress_1star: TextView
    lateinit var recyclerview_comment: RecyclerView
    lateinit var btn_addcomment_nonull: TextView
    lateinit var btn_back_activity:ImageView
    lateinit var Cmtadapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_comment)
        setDialogFullScreen()
        val intent = getIntent()
        progressBar_5star = findViewById(R.id.progressBar_5star)
        progressBar_4star = findViewById(R.id.progressBar_4star)
        progressBar_3star = findViewById(R.id.progressBar_3star)
        progressBar_2star = findViewById(R.id.progressBar_2star)
        progressBar_1star = findViewById(R.id.progressBar_1star)
        rating_comment = findViewById(R.id.rating_comment)
        tvSumStar = findViewById(R.id.tvSumStar)
        tvNumberReview = findViewById(R.id.tvNumberReview)
        phantram_progress_5star = findViewById(R.id.phantram_progress_5star)
        phantram_progress_4star = findViewById(R.id.phantram_progress_4star)
        phantram_progress_3star = findViewById(R.id.phantram_progress_3star)
        phantram_progress_2star = findViewById(R.id.phantram_progress_2star)
        phantram_progress_1star = findViewById(R.id.phantram_progress_1star)
        recyclerview_comment = findViewById(R.id.recyclerview_comment)
        recyclerview_comment.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        btn_addcomment_nonull = findViewById(R.id.btn_addcomment_nonull)

        val maSach = intent.getStringExtra("maSach")
        getReviewComment(maSach)
//        setUpRecyclerview()
        btn_addcomment_nonull.setOnClickListener {
            val intent = Intent(this,AddCommentActivity::class.java)
            intent.putExtra("maSach",maSach)
            this.startActivity(intent)
        }
        btn_back_activity.setOnClickListener {
            onBackPressed()
        }
    }

    fun setUpRecyclerview() {
        val arrayList = ArrayList<CommentModel>()
        arrayList.add(
            CommentModel(
                "Trương Hữu Thắng",
                "29/07/2020",
                4,
                "Cuốn Đắc Nhân Tâm của Dale Cannegie chắc chắn sẽ là một cuốn sâchs gối đầu giường đem đến thành công, hạnh phúc cho bất kì độc giả nào chăm chú đọc nó. Qua cuốn sách này, tác giả đã gửi gắm đến người đọc bài học về cách ứng xử hàng ngày, những phương pháp giao tiếp , nói chuyện sao cho phù hợp, hiệu quả nhất. Bạn có thể học được những kĩ năng mềm, những biện pháp tâm lý cần có trong mọi tình huống,... tất cả những điều ấy sẽ giúp bạn thành công hơn trong công việc cũng như cuộc sống hàng ngày. Còn về mặt hình thức, sách đẹp, bìa cứng rẩt bền, trang trọng, in ấn cũng rất ưng ý ! Fahasa giao hàng nhanh, đóng gói ổn, mình thật sự rất thích !"
            )
        )
        arrayList.add(
            CommentModel(
                "Lê Thanh Tuyên",
                "30/02/2020",
                5,
                "Cuốn Đắc Nhân Tâm của Dale Cannegie chắc chắn sẽ là một cuốn sâchs gối đầu giường đem đến thành công, hạnh phúc cho bất kì độc giả nào chăm chú đọc nó. Qua cuốn sách này, tác giả đã gửi gắm đến người đọc bài học về cách ứng xử hàng ngày, những phương pháp giao tiếp , nói chuyện sao cho phù hợp, hiệu quả nhất. Bạn có thể học được những kĩ năng mềm, những biện pháp tâm lý cần có trong mọi tình huống,... tất cả những điều ấy sẽ giúp bạn thành công hơn trong công việc cũng như cuộc sống hàng ngày. Còn về mặt hình thức, sách đẹp, bìa cứng rẩt bền, trang trọng, in ấn cũng rất ưng ý ! Fahasa giao hàng nhanh, đóng gói ổn, mình thật sự rất thích !"
            )
        )
        Cmtadapter = CommentAdapter(arrayList)
        recyclerview_comment.adapter = Cmtadapter
    }
    private fun setDialogFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            this.window?.statusBarColor = this.resources.getColor(R.color.colorPrimary)
            this.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
    fun getReviewComment(maSach: String) {
        val service =
            RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.getReviewSach(maSach)
        call?.enqueue(object : Callback<ReviewResponse> {
            override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
                Toast.makeText(this@ShowCommentActivity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<ReviewResponse>,
                response: Response<ReviewResponse>
            ) {
                    setViewReviewComment(response)
            }
        })

    }
    private fun setViewReviewComment(response: Response<ReviewResponse>)
    {
        recyclerview_comment.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerview_comment.isNestedScrollingEnabled = false
        val arrayCmt = ArrayList<CommentModel>()
        var demcmt= 0
        response.body()!![0].nhanXets.forEach {
            arrayCmt.add(CommentModel(it.hoTen,it.ngayDang,it.soSao,it.noiDung))
            demcmt++
        }
        tvNumberReview.text = demcmt.toString()+" đánh giá"


        var motsao:Float = (response.body()!![0].motSao.toFloat()/demcmt.toFloat()*100)
        var haisao:Float = (response.body()!![0].haiSao.toFloat()/demcmt.toFloat()*100)
        var basao :Float = (response.body()!![0].baSao.toFloat()/demcmt.toFloat()*100)
        var bonsao:Float = (response.body()!![0].bonSao.toFloat()/demcmt.toFloat()*100)
        var namsao:Float = (response.body()!![0].namSao.toFloat()/demcmt.toFloat()*100)
        var sumStar = (motsao+haisao+basao+bonsao+namsao).toFloat()/100*5
        tvSumStar.text = Math.round(sumStar).toString()
        rating_comment.rating = Math.round(sumStar).toFloat()
        progressBar_1star.secondaryProgress = motsao.toInt()
        progressBar_2star.secondaryProgress = haisao.toInt()
        progressBar_3star.secondaryProgress = basao.toInt()
        progressBar_4star.secondaryProgress = bonsao.toInt()
        progressBar_5star.secondaryProgress = namsao.toInt()
        phantram_progress_1star.text = Math.round(motsao).toString()+"%"
        phantram_progress_2star.text = Math.round(haisao).toString()+"%"
        phantram_progress_3star.text = Math.round(basao).toString()+"%"
        phantram_progress_4star.text = Math.round(bonsao).toString()+"%"
        phantram_progress_5star.text = Math.round(namsao).toString()+"%"
        val adapter = CommentAdapter(arrayCmt)
        recyclerview_comment.adapter = adapter
    }
}