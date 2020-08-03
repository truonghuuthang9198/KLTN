package com.example.kltn

import android.content.Intent
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.screen.*
import com.example.kltn.screen.FormatData.Companion.convertDateFormat
import com.example.kltn.screen.cart.model.CartModel
import com.example.kltn.screen.cart.roomcart.CartViewModel
import com.example.kltn.screen.home.model.BookModel
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.request.AddFavoriteRequest
import com.example.kltn.screen.retrofit.reponse.AddFavoriteResponse
import com.example.kltn.screen.retrofit.reponse.DeleteFavoriteResponse
import com.example.kltn.screen.retrofit.reponse.FavoriteResponse
import com.example.kltn.screen.retrofit.reponse.ReviewResponse
import com.example.kltn.screen.suggest.model.SuggestModel
import com.example.kltn.screen.suggest.roomsuggest.SuggestViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.bottomsheet_dialog_addcart.*
import kotlinx.android.synthetic.main.custom_dialog_login.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat


class DetailActivity() : AppCompatActivity(), Parcelable {
    lateinit var bookModel: BookModel
    lateinit var progressBar_5star: ProgressBar
    lateinit var progressBar_4star: ProgressBar
    lateinit var progressBar_3star: ProgressBar
    lateinit var progressBar_2star: ProgressBar
    lateinit var progressBar_1star: ProgressBar
    lateinit var sum_review: TextView
    lateinit var sum_star: TextView
    lateinit var rating_comment: RatingBar
    lateinit var khach_hang_nx_nonull: TextView
    lateinit var phantram_progress_5star: TextView
    lateinit var phantram_progress_4star: TextView
    lateinit var phantram_progress_3star: TextView
    lateinit var phantram_progress_2star: TextView
    lateinit var phantram_progress_1star: TextView
    lateinit var btnMoveCart: Button
    lateinit var ratingBar: RatingBar
    lateinit var btnAddProductToCart: Button
    lateinit var titlebook: TextView
    lateinit var pricebook: TextView
    lateinit var btn_showdetail_xemthem: Button
    lateinit var priceOriginBook: TextView
    lateinit var tv_showdetail_masach: TextView
    lateinit var tv_showdetail_congtyphathanh: TextView
    lateinit var tv_showdetail_author: TextView
    lateinit var tv_showdetail_nhaxuatban: TextView
    lateinit var tv_showdetail_ngayxuatban: TextView
    lateinit var img_detail_book: ImageView
    lateinit var tv_showdetail_ghichu: TextView
    lateinit var giamgia: TextView
    lateinit var img_tim: ImageView
    lateinit var btn_addcomment_nonull: Button
    lateinit var btn_addcomment_null: Button
    lateinit var progressBarHolder: ProgressBar
    lateinit var constraint_comment_null: ConstraintLayout
    lateinit var constraint_comment_nonull: ConstraintLayout
    lateinit var recyclerview_comment: RecyclerView
    lateinit var btn_showdetail_more_comment: Button
    private lateinit var cartViewModel: CartViewModel
    private lateinit var suggestViewModel: SuggestViewModel


    constructor(parcel: Parcel) : this() {
        bookModel = parcel.readParcelable(BookModel::class.java.classLoader)!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_detail_book)
        asView()
        val intent = getIntent()
        bookModel = intent.getParcelableExtra<BookModel>("deal")
        btn_showdetail_more_comment.setOnClickListener {
            val intent = Intent(this, ShowCommentActivity::class.java)
            intent.putExtra("maSach",bookModel.maSach)
            this.startActivity(intent)
        }
        btn_addcomment_nonull.setOnClickListener {
            val intent = Intent(this, AddCommentActivity::class.java)
            intent.putExtra("maSach",bookModel.maSach)
            this.startActivity(intent)
            // Chuyển fragment add comment
        }
        btn_addcomment_null = findViewById(R.id.btn_addcomment_null)
        btn_addcomment_null.setOnClickListener {
            // Chuyển fragment add comment
            val intent = Intent(this, AddCommentActivity::class.java)
            this.startActivity(intent)
        }



        btnMoveCart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("check", 1)
            this.startActivity(intent)
        }
        setDialogFullScreen()

        cartViewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)
        suggestViewModel = ViewModelProviders.of(this).get(SuggestViewModel::class.java)

        val suggestModel = SuggestModel(bookModel.maTL)
        if (suggestViewModel.countKeySuggest() <= 3) {
            if (suggestViewModel.checkSuggestList(bookModel.maTL) == null) {
                suggestViewModel.insertItemSuggest(suggestModel)
            }
        } else {
            if (suggestViewModel.checkSuggestList(bookModel.maTL) == null) {
                suggestViewModel.insertItemSuggest(suggestModel)
                var arrayListSuggest = ArrayList<SuggestModel>()
                arrayListSuggest = suggestViewModel.getList() as ArrayList<SuggestModel>
                val suggestModelRemove: SuggestModel = arrayListSuggest[arrayListSuggest.size - 1]
                suggestViewModel.deleteItemSuggest(suggestModelRemove)
            }
        }



        titlebook.text = bookModel.tenSach
        //---send data to information product-------------------
        btn_showdetail_xemthem.setOnClickListener {
            val intent = Intent(this, InformationProductActivity::class.java)
            intent.putExtra("InformationBook", bookModel)
            this.startActivity(intent)
        }
        //---------------------------------------------------

        pricebook.text = FormatData.formatMoneyVND(bookModel.giaGiamDS)
        //---------------------------------------------------
        val giamgiaxl = Math.round(bookModel.giamGia * 100)
        val giamgiaString = giamgiaxl.toString() + "%"
        giamgia.text = giamgiaString
        //---------------------------------------------------
        val priceOrigin = FormatData.formatMoneyVND(bookModel.giaban)
        priceOriginBook.text = priceOrigin
        priceOriginBook.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        //---------------------------------------------------
        tv_showdetail_masach.text = bookModel.maSach
        tv_showdetail_congtyphathanh.text = bookModel.maCongTy
        tv_showdetail_author.text = bookModel.maTacGia
        tv_showdetail_nhaxuatban.text = bookModel.maNhaXuatBan
        tv_showdetail_ngayxuatban.text = convertDateFormat(
            bookModel.ngayXuatBan,
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),
            SimpleDateFormat("dd-MM-yyyy")
        )

        tv_showdetail_ghichu.text = bookModel.ghiChu
        ratingBar.rating = bookModel.soSao.toFloat()
        Picasso.get().load(bookModel.hinhAnh).into(img_detail_book)
        try {
            btnAddProductToCart.setOnClickListener {
                val checkItem = cartViewModel.checkExistList(bookModel.maSach)
                if (checkItem == null) {
                    cartViewModel.insertItemCart(
                        CartModel(
                            bookModel.maSach,
                            bookModel.tenSach,
                            1,
                            bookModel.giaGiamDS,
                            bookModel.hinhAnh
                        )
                    )
                    setUpBottomSheetDiaLog()
                } else {
                    cartViewModel.updateSL(bookModel.maSach, checkItem.soLuong + 1)
                    setUpBottomSheetDiaLog()
                }
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


        var checkImageFavorite: Boolean = false
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        var token = pref.getString("TokenLocal", "")
        val service = RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.getListFavorite("Bearer " + token)
        call?.enqueue(object : Callback<List<FavoriteResponse>> {
            override fun onFailure(call: Call<List<FavoriteResponse>>, t: Throwable) {
                Toast.makeText(this@DetailActivity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<FavoriteResponse>>,
                response: Response<List<FavoriteResponse>>
            ) {
                progressBarHolder.visibility = View.GONE
                if (response.isSuccessful) {
                    response.body()?.forEach {
                        if (it.maSach == bookModel.maSach) {
                            checkImageFavorite = true
                            img_tim.setImageResource(R.drawable.ic_favorite_fill)
                        }
                    }
                }
            }
        })

        img_tim.setOnClickListener {
            if (token == "") {
                setUpAlertDiaLog()
            } else {
                progressBarHolder.visibility = View.VISIBLE
                if (checkImageFavorite == true) {
                    val maKH = pref.getString("MaKH", "")
                    val service =
                        RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
                    val call = service?.deleteFavorite("Bearer " + token, maKH!!, bookModel.maSach)
                    call?.enqueue(object : Callback<DeleteFavoriteResponse> {
                        override fun onFailure(call: Call<DeleteFavoriteResponse>, t: Throwable) {
                            Toast.makeText(this@DetailActivity, t.message, Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(
                            call: Call<DeleteFavoriteResponse>,
                            response: Response<DeleteFavoriteResponse>
                        ) {
                            progressBarHolder.visibility = View.GONE
                            Toast.makeText(
                                this@DetailActivity,
                                response.body()!!.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
                    img_tim.setImageResource(R.drawable.ic_favorite_empty)
                    checkImageFavorite = false
                } else {
                    val maKH = pref.getString("MaKH", "")
                    val addFavoriteRequest = AddFavoriteRequest(maKH!!, bookModel.maSach)
                    val service =
                        RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
                    val call = service?.addBookFavorite("Bearer " + token, addFavoriteRequest)
                    call?.enqueue(object : Callback<AddFavoriteResponse> {
                        override fun onFailure(call: Call<AddFavoriteResponse>, t: Throwable) {
                            Toast.makeText(this@DetailActivity, t.message, Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(
                            call: Call<AddFavoriteResponse>,
                            response: Response<AddFavoriteResponse>
                        ) {
                            progressBarHolder.visibility = View.GONE
                            Toast.makeText(
                                this@DetailActivity,
                                response.body()!!.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
                    checkImageFavorite = true
                    img_tim.setImageResource(R.drawable.ic_favorite_fill)
                }
            }
        }
        getReviewComment(bookModel.maSach)
    }

    private fun setUpAlertDiaLog() {
        val customView =
            LayoutInflater.from(this).inflate(R.layout.custom_dialog_login, null, false)
        val dialog = AlertDialog.Builder(this).setView(customView).create()
        dialog.show()
        dialog.btn_huy_dialog_login.setOnClickListener {
            dialog.dismiss()
        }
        dialog.btn_ok_dialog_login.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("check", 2)
            this.startActivity(intent)
            dialog.dismiss()
        }
    }

    private fun asView() {
        recyclerview_comment = findViewById(R.id.recyclerview_comment)
        progressBarHolder = findViewById(R.id.progressBarHolder)
        progressBarHolder.visibility = View.VISIBLE
        ratingBar = findViewById<RatingBar>(R.id.rating)
        img_tim = findViewById(R.id.img_tim)
        btnMoveCart = findViewById(R.id.btn_move_cart)
        titlebook = findViewById(R.id.title_book)
        priceOriginBook = findViewById(R.id.tv_priceorigin_showdetail)
        pricebook = findViewById(R.id.tv_priceBook_showdetail)
        giamgia = findViewById(R.id.tv_giamgia_showdetail)
        tv_showdetail_masach = findViewById(R.id.tv_showdetail_masach)
        tv_showdetail_congtyphathanh = findViewById(R.id.tv_showdetail_ctyphathanh)
        tv_showdetail_author = findViewById(R.id.tv_showdetail_author)
        tv_showdetail_nhaxuatban = findViewById(R.id.tv_showdetail_nhaxuatban)
        tv_showdetail_ngayxuatban = findViewById(R.id.tv_showdetail_ngayxuatban)
        tv_showdetail_ghichu = findViewById(R.id.tv_showdetail_ghichu)
        img_detail_book = findViewById(R.id.img_detail_book)
        btn_showdetail_xemthem = findViewById(R.id.btn_showdetail_xemthem)
        btnAddProductToCart = findViewById(R.id.btn_add_product_to_cart)
        constraint_comment_nonull = findViewById(R.id.constraint_comment_nonull)

        btn_showdetail_more_comment = findViewById(R.id.btn_showdetail_more_comment)
        constraint_comment_null = findViewById(R.id.constraint_comment_null)
        btn_addcomment_nonull = findViewById(R.id.btn_addcomment_nonull)

        progressBar_5star = findViewById(R.id.progressBar_5star)
        progressBar_4star = findViewById(R.id.progressBar_4star)
        progressBar_3star = findViewById(R.id.progressBar_3star)
        progressBar_2star = findViewById(R.id.progressBar_2star)
        progressBar_1star = findViewById(R.id.progressBar_1star)
        sum_review = findViewById(R.id.sum_review)
        sum_star = findViewById(R.id.sum_star)
        rating_comment = findViewById(R.id.rating_comment)
        khach_hang_nx_nonull = findViewById(R.id.khach_hang_nx_nonull)
        phantram_progress_5star = findViewById(R.id.phantram_progress_5star)
        phantram_progress_4star = findViewById(R.id.phantram_progress_4star)
        phantram_progress_3star = findViewById(R.id.phantram_progress_3star)
        phantram_progress_2star = findViewById(R.id.phantram_progress_2star)
        phantram_progress_1star = findViewById(R.id.phantram_progress_1star)
    }

    private fun setUpBottomSheetDiaLog() {
        val customView =
            LayoutInflater.from(this).inflate(R.layout.bottomsheet_dialog_addcart, null, false)

        val dialog = BottomSheetDialog(this)
        dialog.setContentView(customView)
        dialog.create()
        dialog.show()
        dialog.btn_tieptuc_muahang.setOnClickListener {
            dialog.dismiss()
        }
        dialog.btn_xemgiohang.setOnClickListener {
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

    fun getReviewComment(maSach: String) {
        val service =
            RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.getReviewSach(maSach)
        call?.enqueue(object : Callback<ReviewResponse> {
            override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
                Toast.makeText(this@DetailActivity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<ReviewResponse>,
                response: Response<ReviewResponse>
            ) {
                if(response.body()!!.isEmpty()) {
                    constraint_comment_nonull.visibility = View.GONE
                    constraint_comment_null.visibility = View.VISIBLE
                }
                else
                {
                    constraint_comment_null.visibility = View.GONE
                    constraint_comment_nonull.visibility = View.VISIBLE
                    setViewReviewComment(response)
                    progressBarHolder.visibility = View.GONE

                }
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
        khach_hang_nx_nonull.text = "KHÁCH HÀNG NHẬN XÉT ("+demcmt.toString()+" ĐÁNH GIÁ)"
        sum_review.text = demcmt.toString()+" đánh giá"


        var motsao:Float = (response.body()!![0].motSao.toFloat()/demcmt.toFloat()*100)
        var haisao:Float = (response.body()!![0].haiSao.toFloat()/demcmt.toFloat()*100)
        var basao :Float = (response.body()!![0].baSao.toFloat()/demcmt.toFloat()*100)
        var bonsao:Float = (response.body()!![0].bonSao.toFloat()/demcmt.toFloat()*100)
        var namsao:Float = (response.body()!![0].namSao.toFloat()/demcmt.toFloat()*100)
        var sumStar = (5*response.body()!![0].namSao+4*response.body()!![0].bonSao+3*response.body()!![0].baSao+2*response.body()!![0].haiSao+response.body()!![0].motSao)
        var tbStar: Float = sumStar.toFloat()/demcmt.toFloat()
        sum_star.text = (Math.round(tbStar*100).toFloat()/100).toString()
        rating_comment.rating = Math.round(tbStar*100).toFloat()/100
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

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            this.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .addToBackStack(null)
                .commit()
            return true
        }
        return false
    }
}