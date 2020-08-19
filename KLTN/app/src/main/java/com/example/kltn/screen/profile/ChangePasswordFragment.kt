package com.example.kltn.screen.profile

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.profile.adapter.FavoriteAdapter
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.reponse.CheckLoginResponse
import com.example.kltn.screen.retrofit.reponse.LoginResponse
import com.example.kltn.screen.retrofit.reponse.RegisterResponse
import com.example.kltn.screen.retrofit.reponse.UpdatePasswordResponse
import com.example.kltn.screen.retrofit.request.RegisterRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordFragment(val responseUser: LoginResponse) : Fragment() {
    lateinit var edt_nhapmkcu: EditText
    lateinit var edt_nhapmatkhaumoi: EditText
    lateinit var edt_xacnhanmkmoi: EditText
    lateinit var btn_back_doimatkhau: ImageView
    lateinit var btn_xacnhan_doimk: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_change_password, container, false)
        btn_back_doimatkhau = view.findViewById(R.id.btn_back_doimatkhau)
        edt_nhapmkcu = view.findViewById(R.id.edt_nhapmkcu)
        edt_nhapmatkhaumoi = view.findViewById(R.id.edt_nhapmatkhaumoi)
        edt_xacnhanmkmoi = view.findViewById(R.id.edt_xacnhanmkmoi)
        btn_xacnhan_doimk = view.findViewById(R.id.btn_xacnhan_doimk)
        btn_back_doimatkhau.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }
        btn_xacnhan_doimk.setOnClickListener {
            if (edt_nhapmatkhaumoi.text.toString().compareTo(edt_xacnhanmkmoi.text.toString())==0)
            {
                val pref = PreferenceManager.getDefaultSharedPreferences(context)
                var token = pref.getString("TokenLocal","")
                if (token != null) {
                    getUserwithToken(token)

                }

            }
            else
            {
                Toast.makeText(activity,"Xác nhận mật khẩu không trùng khớp",Toast.LENGTH_LONG).show()
            }

        }
        return view
    }

    fun updatePassword() {
        val registerRequest = RegisterRequest(
            responseUser.maKhachHang,
            edt_xacnhanmkmoi.text.toString(),
            responseUser.tenKhachHang,
            responseUser.diaChi,
            responseUser.soDienThoai,
            responseUser.email,
            responseUser.gioiTinh,
            responseUser.capDoThanhVien
        )
        val id = responseUser.maKhachHang
        val service =
            RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.updatePassword(id,registerRequest)
        call?.enqueue(object : Callback<UpdatePasswordResponse> {
            override fun onFailure(call: Call<UpdatePasswordResponse>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<UpdatePasswordResponse>,
                response: Response<UpdatePasswordResponse>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(activity, "Đổi mật khẩu thành công", Toast.LENGTH_LONG).show()
                    if (fragmentManager!!.backStackEntryCount > 0) {
                        fragmentManager!!.popBackStack()
                    }
                } else {
                    Toast.makeText(activity, "Đổi mật khẩu thất bại", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    fun getUserwithToken(token: String) {
        val service = RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
        val call = service?.getUserWithToken("Bearer " + token)
        call?.enqueue(object : Callback<CheckLoginResponse> {
            override fun onFailure(call: Call<CheckLoginResponse>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<CheckLoginResponse>,
                response: Response<CheckLoginResponse>
            ) {
                if (response.body()?.success == true) {
                    if (response.body()!!.khachHang.matKhau.compareTo(edt_nhapmkcu.text.toString()) == 0) {
                        updatePassword()
                    }
                    else
                    {
                        Toast.makeText(activity,"Vui lòng kiểm tra mật khẩu cũ",Toast.LENGTH_LONG).show()
                    }
                }

            }
        })
    }
}