package com.example.kltn.screen.profile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.profile.model.HistoryBillModel
import com.example.kltn.screen.profile.model.HistoryDetailBillModel
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class HistoryBillAdapter internal constructor(var context: Context?, var listHistory: ArrayList<HistoryBillModel>) :
    RecyclerView.Adapter<HistoryBillAdapter.HistoryBillViewHolder>() {
    inner class HistoryBillViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_id_donhang: TextView = itemView.findViewById(R.id.tv_id_donhang_history_salebook)
        val tv_ngaydathang: TextView = itemView.findViewById(R.id.tv_ngaydathang_history_salebook)
        val tv_thanhtien: TextView = itemView.findViewById(R.id.tv_thanhtien_history_salebook)
        val btn_xemchitiet: TextView = itemView.findViewById(R.id.btn_xemchitiet)
        val recyclerview_detail_history_bill: RecyclerView = itemView.findViewById(R.id.recyclerview_detail_history_bill)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryBillViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recyclerview_item_history_salebook, parent, false)
        val HistoryBillViewHolder = HistoryBillViewHolder(cellForRow)
        return HistoryBillViewHolder
    }

    override fun getItemCount() = listHistory.size

    override fun onBindViewHolder(holder: HistoryBillViewHolder, position: Int) {
        var check:Int = 1
        val arrayList = ArrayList<HistoryDetailBillModel>()
        val current = listHistory[position]
        holder.tv_id_donhang.append(current.idHD)
        holder.tv_ngaydathang.append(current.ngayLap)
        val localVN = Locale("vi","VN")
        val numberFormat = NumberFormat.getCurrencyInstance(localVN)
        val giatienfm =numberFormat.format(current.thanhTien)
        holder.tv_thanhtien.append(giatienfm)
        holder.recyclerview_detail_history_bill.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        holder.recyclerview_detail_history_bill.visibility = View.GONE
        holder.btn_xemchitiet.setOnClickListener {
            if(check == 1)
            {
                holder.recyclerview_detail_history_bill.visibility = View.VISIBLE
                holder.btn_xemchitiet.text = "Thu gọn"
                holder.btn_xemchitiet.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_up,0)
                holder.recyclerview_detail_history_bill.adapter = HistoryDetailBillAdapter(context,arrayList)
                check = 0
            }
            else
            {
                holder.btn_xemchitiet.text = "Xem chi tiết đơn hàng"
                holder.btn_xemchitiet.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_expand_more,0)
                holder.recyclerview_detail_history_bill.visibility = View.GONE
                check=1
            }
        }

        arrayList.add(
            HistoryDetailBillModel(0,
                0,
                "Nghỉ hè năm lớp 12 dầu sôi lửa bỏng, Bossun lại bận bù đầu (không phải vì học đâu ạ!)… đi biển, làm trợ lí cho họa sĩ manga và thậm trí còn lên đường du ngoạn bằng… xe đạp một mình! Còn nữa, nhất định phải xem cuộc gặp gỡ kì thú giữa bộ ba Sket Dan và cặp đôi hài của “Viking” các bạn nhé!",
                18000.00,
                0.05,
                "https://cdn0.fahasa.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/s/k/sket-dance---quai-kiet--hoc-duong-tap-30.jpg",
                "17.6 x 11.3 cm",
                "Bìa Mềm",
                "Nhà Xuất Bản Kim Đồng",
                "8935244841961",
                "TG001",
                "Tiểu thuyết",
                "2020",
                "NXB Kim Đồng",
                "200 Trang",
                "Kenta Shinohara",
                "Sket Dance - Quái Kiệt Học Đường - Tập 30",
                2,200000.00, 2)
        )
        arrayList.add(
            HistoryDetailBillModel(0,
                0,
                "Nghỉ hè năm lớp 12 dầu sôi lửa bỏng, Bossun lại bận bù đầu (không phải vì học đâu ạ!)… đi biển, làm trợ lí cho họa sĩ manga và thậm trí còn lên đường du ngoạn bằng… xe đạp một mình! Còn nữa, nhất định phải xem cuộc gặp gỡ kì thú giữa bộ ba Sket Dan và cặp đôi hài của “Viking” các bạn nhé!",
                18000.00,
                0.05,
                "https://cdn0.fahasa.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/s/k/sket-dance---quai-kiet--hoc-duong-tap-30.jpg",
                "17.6 x 11.3 cm",
                "Bìa Mềm",
                "Nhà Xuất Bản Kim Đồng",
                "8935244841961",
                "TG001",
                "Tiểu thuyết",
                "2020",
                "NXB Kim Đồng",
                "200 Trang",
                "Kenta Shinohara",
                "Sket Dance - Quái Kiệt Học Đường - Tập 30",
                2,200000.00, 2)
        )
        arrayList.add(
            HistoryDetailBillModel(0,
                0,
                "Nghỉ hè năm lớp 12 dầu sôi lửa bỏng, Bossun lại bận bù đầu (không phải vì học đâu ạ!)… đi biển, làm trợ lí cho họa sĩ manga và thậm trí còn lên đường du ngoạn bằng… xe đạp một mình! Còn nữa, nhất định phải xem cuộc gặp gỡ kì thú giữa bộ ba Sket Dan và cặp đôi hài của “Viking” các bạn nhé!",
                18000.00,
                0.05,
                "https://cdn0.fahasa.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/s/k/sket-dance---quai-kiet--hoc-duong-tap-30.jpg",
                "17.6 x 11.3 cm",
                "Bìa Mềm",
                "Nhà Xuất Bản Kim Đồng",
                "8935244841961",
                "TG001",
                "Tiểu thuyết",
                "2020",
                "NXB Kim Đồng",
                "200 Trang",
                "Kenta Shinohara",
                "Sket Dance - Quái Kiệt Học Đường - Tập 30",
                2,200000.00, 2)
        )
    }
}