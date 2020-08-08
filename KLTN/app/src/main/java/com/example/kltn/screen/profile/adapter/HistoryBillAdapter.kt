package com.example.kltn.screen.profile.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.FormatData
import com.example.kltn.screen.FormatData.Companion.convertDateFormat
import com.example.kltn.screen.home.model.BookModel
import com.example.kltn.screen.profile.model.HistoryBillModel
import com.example.kltn.screen.profile.model.HistoryDetailBillModel
import com.example.kltn.screen.retrofit.reponse.HistoryResponse
import retrofit2.Response
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class HistoryBillAdapter internal constructor(
    var context: Context?,
    var listHistory: ArrayList<HistoryBillModel>,
    var response: Response<List<HistoryResponse>>
) :
    RecyclerView.Adapter<HistoryBillAdapter.HistoryBillViewHolder>() {
    inner class HistoryBillViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_id_donhang: TextView = itemView.findViewById(R.id.tv_id_donhang_history_salebook)
        val tv_ngaydathang: TextView = itemView.findViewById(R.id.tv_ngaydathang_history_salebook)
        val tv_thanhtien: TextView = itemView.findViewById(R.id.tv_thanhtien_history_salebook)
        val btn_xemchitiet: TextView = itemView.findViewById(R.id.btn_xemchitiet)
        val recyclerview_detail_history_bill: RecyclerView =
            itemView.findViewById(R.id.recyclerview_detail_history_bill)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryBillViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow =
            layoutInflater.inflate(R.layout.recyclerview_item_history_salebook, parent, false)
        val HistoryBillViewHolder = HistoryBillViewHolder(cellForRow)
        return HistoryBillViewHolder
    }

    override fun getItemCount() = listHistory.size


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: HistoryBillViewHolder, position: Int) {
        var check: Int = 1
        val arrayList = ArrayList<BookModel>()
        val current = listHistory[position]
        holder.tv_id_donhang.append(current.idHD)
        holder.tv_ngaydathang.append(
            convertDateFormat(
                current.ngayLap,
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),
                SimpleDateFormat("dd/MM/yyyy")
            )
        )
        holder.tv_thanhtien.append(FormatData.formatMoneyVND(current.thanhTien))
        holder.recyclerview_detail_history_bill.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        holder.recyclerview_detail_history_bill.visibility = View.GONE
        response.body()!!.forEach {
            if (it.maHoaDon == current.idHD) {
                it.chiTietHoaDons.forEach {
                    arrayList.add(
                        BookModel(
                            0,
                            0,
                            it.sach.ghiChu,
                            it.donGia,
                            it.sach.giamGia,
                            it.sach.hinhAnh,
                            it.sach.kichThuoc,
                            it.sach.loaiBia,
                            it.sach.congTyPhatHanh.tenCongTy,
                            it.sach.maSach,
                            it.sach.tacGia.tenTacGia,
                            it.sach.theLoai.tenTheLoai,
                            it.sach.maTheLoai,
                            it.sach.ngayXuatBan,
                            it.sach.nhaXuatBan.tenNhaXuatBan,
                            it.sach.soLuong,
                            it.sach.soTrang,
                            it.sach.tenSach,
                            it.sach.tinhTrang,
                            it.sach.soSao
                        )
                    )
                }
            }
        }
        holder.btn_xemchitiet.setOnClickListener {
            if (check == 1) {
                holder.recyclerview_detail_history_bill.visibility = View.VISIBLE
                holder.btn_xemchitiet.text = "Thu gọn"
                holder.btn_xemchitiet.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_up,
                    0
                )
                holder.recyclerview_detail_history_bill.adapter =
                    HistoryDetailBillAdapter(context, arrayList)
                check = 0
            } else {
                holder.btn_xemchitiet.text = "Xem chi tiết đơn hàng"
                holder.btn_xemchitiet.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_expand_more,
                    0
                )
                holder.recyclerview_detail_history_bill.visibility = View.GONE
                check = 1
            }
        }
    }
}