package com.example.kltn.screen.profile



import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.kltn.R
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.model.LoginModel
import com.example.kltn.screen.retrofit.reponse.LoginResponse
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.onesignal.OneSignal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


@Suppress("DEPRECATION")
class LoginFragment : Fragment() {
    private var callBackManager: CallbackManager? = null
    private var auth: FirebaseAuth? = null
    lateinit var btnDangNhap: Button
    lateinit var edit_email_sdt: EditText
    lateinit var edit_password: EditText
    lateinit var btnDangNhapFB: Button
    lateinit var tvDangKiTaiKhoan: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mOnInputSelected = targetFragment as OnInputSelected?
        } catch (e: ClassCastException) {
            Log.e("ThangTruong", "onAttach: ClassCastException : " + e.message)
        }
    }
    interface OnInputSelected {
        fun sendInput(tabid: Int)
    }

    var mOnInputSelected: OnInputSelected? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        btnDangNhap = view.findViewById(R.id.btn_dangnhap)
        btnDangNhapFB = view.findViewById(R.id.btn_dangnhap_fb)
        edit_email_sdt = view.findViewById(R.id.edit_email_sdt)
        edit_password = view.findViewById(R.id.edit_password)
        tvDangKiTaiKhoan =view.findViewById(R.id.tvDangKiTaiKhoan)
        callBackManager = CallbackManager.Factory.create()
        btnDangNhap.setOnClickListener {

            val tendn = edit_email_sdt.text.toString()
            val mk = edit_password.text.toString()
            //----Login----------------------
            val checklogin = LoginModel(tendn,mk)
            val service = RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
            val call = service?.login(checklogin)
            call?.enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if ( response.isSuccessful) {
                        //Lưu token vào local
                        val pref = PreferenceManager.getDefaultSharedPreferences(activity!!)
                        val edit = pref.edit()
                        edit.putString("Token",response.body()!!.token)
                        edit.putString("MaKH",response.body()!!.maKhachHang)
                        edit.apply()
                       //Gửi id thiết bị lên sever
                        val status = OneSignal.getPermissionSubscriptionState()
                        Log.d("Thang",status.subscriptionStatus.userId)
                        loadFragment(InformationFragment(response.body()!!))
                    }
                    else
                    {
                        Toast.makeText(context,"Sai tài khoản hoặc mật khẩu",Toast.LENGTH_LONG).show()
                    }
                }
            })
//            val loginResponse = LoginResponse(null,null,null,null,null,null)
//            loadFragment(InformationFragment(loginResponse))
        }

        tvDangKiTaiKhoan.setOnClickListener {
            mOnInputSelected!!.sendInput(2)
        }
        btnDangNhapFB.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(activity!!,Arrays.asList("public_profile","email"))
            LoginManager.getInstance().registerCallback(callBackManager,object : FacebookCallback<LoginResult>{
                override fun onSuccess(result: LoginResult?) {
                    handleFacebookAccessToken(result?.accessToken)
                    Toast.makeText(activity!!,result!!.toString(),Toast.LENGTH_LONG).show()
                }
                override fun onCancel() {
                }
                override fun onError(error: FacebookException?) {
                }
            })
//            val pref = PreferenceManager.getDefaultSharedPreferences(activity!!)
//            val edit = pref.edit()
//            edit.putBoolean("CheckLogin",true)
//            edit.apply()
        }
        return view

    }

    private fun handleFacebookAccessToken(accessToken: AccessToken?) {
        var credential = FacebookAuthProvider.getCredential(accessToken?.token!!)
        auth?.signInWithCredential(credential)?.addOnCompleteListener {
                task ->
            if (task.isSuccessful)
            {
//                loadFragment(InformationFragment())
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callBackManager?.onActivityResult(requestCode,resultCode,data)
    }
    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit()
            return true
        }
        return false
    }
}
