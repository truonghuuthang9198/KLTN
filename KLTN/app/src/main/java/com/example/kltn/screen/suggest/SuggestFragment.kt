package com.example.kltn.screen.suggest


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.home.model.BookModel
import com.example.kltn.screen.suggest.adapter.SuggestAdapter
import com.google.common.eventbus.Subscribe
import de.greenrobot.event.EventBus

/**
 * A simple [Fragment] subclass.
 */
class SuggestFragment : Fragment() {
    lateinit var recyclerviewSuggest: RecyclerView
    lateinit var suggestAdapter: SuggestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_suggest, container, false)
        recyclerviewSuggest = view.findViewById(R.id.recyclerview_suggest)
        recyclerviewSuggest.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )

        recyclerviewSuggest.layoutManager = GridLayoutManager(activity, 2)
        setUpRecyclerview()
        return view
    }

    fun setUpRecyclerview() {
        val arrayList = ArrayList<BookModel>()
        arrayList.add(
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
                "Còn hàng", 2
            )
        )
        arrayList.add(
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
                2
            )
        )
        arrayList.add(
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
                3
            )
        )
        arrayList.add(
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
                3
            )
        )
        suggestAdapter = SuggestAdapter(arrayList)
        recyclerviewSuggest.adapter = suggestAdapter
    }
}
