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
import androidx.lifecycle.ViewModelProviders
import com.example.kltn.screen.FormatData
import com.example.kltn.screen.FormatData.Companion.convertDateFormat
import com.example.kltn.screen.cart.model.CartModel
import com.example.kltn.screen.cart.roomcart.CartViewModel
import com.example.kltn.screen.home.model.BookModel
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.model.AddFavoriteRequest
import com.example.kltn.screen.retrofit.reponse.AddFavoriteResponse
import com.example.kltn.screen.retrofit.reponse.DeleteFavoriteResponse
import com.example.kltn.screen.retrofit.reponse.FavoriteResponse
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
    lateinit var progressBarHolder: ProgressBar
    private lateinit var cartViewModel: CartViewModel
    private lateinit var suggestViewModel: SuggestViewModel



    constructor(parcel: Parcel) : this() {
        bookModel = parcel.readParcelable(BookModel::class.java.classLoader)!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_detail_book)

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

        btnMoveCart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("check", 1)
            this.startActivity(intent)
        }
        setDialogFullScreen()

        cartViewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)
        suggestViewModel = ViewModelProviders.of(this).get(SuggestViewModel::class.java)
        val intent = getIntent()
        bookModel = intent.getParcelableExtra<BookModel>("deal")

        val suggestModel = SuggestModel(bookModel.maTL)
        if (suggestViewModel.countKeySuggest() <=3) {
            if (suggestViewModel.checkSuggestList(bookModel.maTL) == null) {
                suggestViewModel.insertItemSuggest(suggestModel)
            }
        }
        else
        {
            if (suggestViewModel.checkSuggestList(bookModel.maTL) == null) {
                suggestViewModel.insertItemSuggest(suggestModel)
                var arrayListSuggest = ArrayList<SuggestModel>()
                arrayListSuggest = suggestViewModel.getList() as ArrayList<SuggestModel>
                val suggestModelRemove: SuggestModel = arrayListSuggest[arrayListSuggest.size-1]
                suggestViewModel.deleteItemSuggest(suggestModelRemove)
            }
        }

//            } else {

//
//            }





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
            if(token == "")
            {
                setUpAlertDiaLog()
            }
            else {
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

}