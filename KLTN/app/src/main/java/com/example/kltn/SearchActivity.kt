package com.example.kltn

import android.content.Intent
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.screen.SearchAdapter
import com.example.kltn.screen.cart.model.CartModel
import com.example.kltn.screen.cart.roomdatabase.CartViewModel
import com.example.kltn.screen.home.model.BookModel
import com.example.kltn.screen.profile.ProfileFragment
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.reponse.RegisterResponse
import com.example.kltn.screen.retrofit.reponse.SachResponse
import com.example.kltn.screen.retrofit.reponse.SearchResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var recyclerViewSearch: RecyclerView
    lateinit var searchAdapter: SearchAdapter
    private var editSearchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setDialogFullScreen()
        recyclerViewSearch = findViewById(R.id.recyclerview_search)
        editSearchView = findViewById(R.id.search_text)
        editSearchView!!.setOnQueryTextListener(this)
        val intent = getIntent()
        val query = intent.getStringExtra("keySearch")
        editSearchView!!.setQuery(query, true)
        getListSearch(query)
        //setUpRecyclerview()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {

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


    private fun setUpRecyclerview() {
        recyclerViewSearch.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewSearch.layoutManager = GridLayoutManager(this, 2)
        val arrayListSearch = ArrayList<BookModel>()
        arrayListSearch.add(
            BookModel(
                0,
                0,
                "Nghỉ hè năm lớp 12 dầu sôi lửa bỏng, Bossun lại bận bù đầu (không phải vì học đâu ạ!)… đi biển, làm trợ lí cho họa sĩ manga và thậm trí còn lên đường du ngoạn bằng… xe đạp một mình! Còn nữa, nhất định phải xem cuộc gặp gỡ kì thú giữa bộ ba Sket Dan và cặp đôi hài của “Viking” các bạn nhé!",
                18000.00,
                0.05,
                "https://cdn0.fahasa.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/s/k/sket-dance---quai-kiet--hoc-duong-tap-30.jpg",
                "17.6 x 11.3 cm",
                "Bìa Mềm",
                "Nhà Xuất Bản Kim Đồng",
                "8935244841961",
                "Kenta Shinohara",
                "Tiểu thuyết",
                "2020",
                "NXB Kim Đồng",
                2,
                "188 Trang",
                "Sket Dance - Quái Kiệt Học Đường - Tập 30",
                "Còn hàng",
                3
            )
        )
        arrayListSearch.add(
            BookModel(
                1,
                0,
                "\"Mr. Lemoncello is going live with a brand-new televised BREAKOUT game! Discover what James Patterson calls \"\"the coolest library in the world\"\" in the fourth puzzle-packed adventure in Chris Grabenstein's New York Times bestselling MR. LEMONCELLO series! Greetings boys and girls, gamers of all ages--are you ready to play Mr. Lemoncello's BIGGEST, most dazzling game yet?!\n" +
                        "\n" +
                        "After months of anticipation, Mr. Lemoncello is taking his games out of the library and LIVE across the nation on the world famous Kidzapalooza Television Network! Everyone's invited to audition, but only a lucky few will be chosen to compete in front of millions of viewers in a brand new, completely immersive, live action breakout game where kids will be the playing pieces! Kyle Keeley is determined to be one of them. Each of the winning teams will have to make it through 5 separate rooms in Mr. Lemoncello's fantastic new Fictionasium to find the answer to a puzzle that will unlock that room's lock and allow them to break out! But nothing is ever as it seems with Mr. Lemoncello and the surprises in store just might stump even the gamemaster himself. Can Kyle break out of his own expectations-and win Mr. Lemoncello's ultimate gameshow?\"",
                168000.00,
                0.25,
                "https://cdn0.fahasa.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/i/m/image_195509_1_13256.jpg",
                "21 x 2.4 x 14 cm",
                "Bìa Mềm",
                "Random House",
                "9780593118399",
                "Chris Grabenstein",
                "Truyện Tranh",
                "2020",
                "NXB Kim Đồng",
                2,
                "304 Trang",
                "Mr. Lemoncello's All-Star Breakout Game (Mr. Lemoncello'S Library)",
                "Còn hàng",
                4
            )
        )
        arrayListSearch.add(
            BookModel(
                2,
                0,
                "Nghỉ hè năm lớp 12 dầu sôi lửa bỏng, Bossun lại bận bù đầu (không phải vì học đâu ạ!)… đi biển, làm trợ lí cho họa sĩ manga và thậm trí còn lên đường du ngoạn bằng… xe đạp một mình! Còn nữa, nhất định phải xem cuộc gặp gỡ kì thú giữa bộ ba Sket Dan và cặp đôi hài của “Viking” các bạn nhé!",
                266900.00,
                0.15,
                "https://cdn0.fahasa.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/9/7/9781481497619.jpg",
                "16 x 24 cm",
                "Bìa Cứng",
                "Simon And Schuster",
                "9781481497619",
                "Margaret Rogerson",
                "Tiểu thuyết",
                "2019",
                "Margaret K. McElderry Books",
                2,
                "188 Trang",
                "Sorcery of Thorns",
                "Còn hàng",
                5
            )
        )
        searchAdapter = SearchAdapter(arrayListSearch)
        recyclerViewSearch.adapter = searchAdapter
    }

    fun getListSearch(id: String) {
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
                if (response.isSuccessful) {
                    setUpRecyclerView(response)
                } else {
                    Toast.makeText(
                        this@SearchActivity,
                        "Không tìm thấy sách theo yêu cầu",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    fun setUpRecyclerView(response: Response<List<SearchResponse>>) {
        recyclerViewSearch.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewSearch.layoutManager = GridLayoutManager(this, 2)
        val arrayListSearch = ArrayList<BookModel>()
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
