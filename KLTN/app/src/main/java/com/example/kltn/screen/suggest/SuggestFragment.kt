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
import com.example.kltn.screen.home.model.ShowMoreDealModel
import com.example.kltn.screen.profile.model.SendArrayAddress
import com.example.kltn.screen.suggest.adapter.SuggestAdapter
import com.example.kltn.screen.suggest.model.SuggestModel
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
        val arrayList = ArrayList<SuggestModel>()
        arrayList.add(
            SuggestModel(R.drawable.vd3_sach, "Dạy Tiếng Anh Xu Hướng Mới", 33400.00, 56000.00, 2)
        )
        arrayList.add(
            SuggestModel(R.drawable.vd3_sach, "Thanh Gươm Diệt Quỷ Tập 5", 23750.00, 25000.00, 5)
        )
        arrayList.add(
            SuggestModel(R.drawable.vd3_sach, "Pokemon Đặc Biệt - Tập 1", 23750.00, 25000.00, 1)
        )
        arrayList.add(
            SuggestModel(R.drawable.vd3_sach, "Pokemon Đặc Biệt - Tập 2", 23750.00, 25000.00, 3)
        )
        arrayList.add(
            SuggestModel(R.drawable.vd3_sach, "Pokemon Đặc Biệt - Tập 1", 23750.00, 25000.00, 1)
        )
        arrayList.add(
            SuggestModel(R.drawable.vd3_sach, "Pokemon Đặc Biệt - Tập 2", 23750.00, 25000.00, 3)
        )
        suggestAdapter = SuggestAdapter(arrayList)
        recyclerviewSuggest.adapter = suggestAdapter
    }
}
