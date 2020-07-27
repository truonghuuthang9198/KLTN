package com.example.kltn

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.screen.SearchAdapter
import com.example.kltn.screen.home.model.BookModel
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.reponse.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var recyclerViewSearch: RecyclerView
    lateinit var searchAdapter: SearchAdapter
    private var editSearchView: SearchView? = null
    lateinit var btn_back_activity_search: ImageView
    lateinit var search_null: ConstraintLayout
    lateinit var btn_back_home_searchActivity: ImageView
    lateinit var progressBarHolder: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setDialogFullScreen()
        recyclerViewSearch = findViewById(R.id.recyclerview_search)
        btn_back_activity_search = findViewById(R.id.btn_back_activity_search)
        progressBarHolder = findViewById(R.id.progressBarHolder)
        search_null = findViewById(R.id.search_null)
        search_null.visibility = View.GONE
        btn_back_home_searchActivity = findViewById(R.id.btn_back_home_searchActivity)
        btn_back_activity_search.setOnClickListener {
            onBackPressed()
        }
        btn_back_home_searchActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        editSearchView = findViewById(R.id.search_text)
        editSearchView!!.setOnQueryTextListener(this)
        val intent = getIntent()
        val query = intent.getStringExtra("keySearch")
        editSearchView!!.setQuery(query, true)
        getListSearch(query)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        getListSearch(query!!)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        return false
    }

    private fun setDialogFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            this.window?.statusBarColor = this.resources.getColor(R.color.colorPrimary)
            this.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    fun getListSearch(id: String) {
        progressBarHolder.visibility = View.VISIBLE
        val service =
            RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.getListSearch(id)
        call?.enqueue(object : Callback<List<SearchResponse>> {
            override fun onFailure(call: Call<List<SearchResponse>>, t: Throwable) {
                Toast.makeText(this@SearchActivity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<SearchResponse>>,
                response: Response<List<SearchResponse>>
            ) {
                if (response.isSuccessful && response.body()!!.isNotEmpty()) {
                    progressBarHolder.visibility = View.GONE
                    recyclerViewSearch.visibility = View.VISIBLE
                    setUpRecyclerView(response)
                } else {
                    progressBarHolder.visibility = View.GONE
                    recyclerViewSearch.visibility = View.GONE
                    search_null.visibility = View.VISIBLE
                }
            }
        })
    }

    fun setUpRecyclerView(response: Response<List<SearchResponse>>) {
        recyclerViewSearch.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewSearch.layoutManager = GridLayoutManager(this, 2)
        val arrayListSearch = ArrayList<BookModel>()
        if(response.body()!!.isNotEmpty()) {
            response.body()!!.forEach {
                arrayListSearch.add(
                    BookModel(
                        0,
                        0,
                        it.ghiChu,
                        it.giaBan,
                        it.giamGia,
                        it.hinhAnh,
                        it.kichThuoc,
                        it.loaiBia,
                        it.maCongTy,
                        it.maSach,
                        it.tenTacGia,
                        it.tenTheLoai,
                        it.maTheLoai,
                        it.ngayXuatBan,
                        it.tenNhaXuatBan,
                        it.soLuong,
                        it.soTrang,
                        it.tenSach,
                        it.tinhTrang,
                        it.soSao
                    )
                )
            }
            searchAdapter = SearchAdapter(arrayListSearch)
            recyclerViewSearch.adapter = searchAdapter
        }
    }
}
