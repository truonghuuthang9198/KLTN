package com.example.kltn.screen.profile

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.kltn.R
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.model.RegisterModel
import com.example.kltn.screen.retrofit.reponse.LoginResponse
import com.example.kltn.screen.retrofit.reponse.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {
    lateinit var edit_email_dangki: EditText
    lateinit var edit_password_dangki: EditText
    lateinit var edit_ho_ten_dangki: EditText
    lateinit var edt_diachi_dangki: EditText
    lateinit var edt_sodienthoai_dangki: EditText
    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton
    lateinit var btn_dangky: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_register, container, false)
        edit_email_dangki = view.findViewById(R.id.edit_email_dangki)
        edit_password_dangki = view.findViewById(R.id.edit_password_dangki)
        edit_ho_ten_dangki = view.findViewById(R.id.edit_ho_ten_dangki)
        edt_diachi_dangki = view.findViewById(R.id.edt_diachi_dangki)
        edt_sodienthoai_dangki = view.findViewById(R.id.edt_sodienthoai_dangki)
        radioGroup = view.findViewById(R.id.radio_group_sex)
        btn_dangky = view.findViewById(R.id.btn_dangky)
        btn_dangky.setOnClickListener {
            val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
            radioButton = view.findViewById(intSelectButton)
            val registerModel = RegisterModel(
                edit_email_dangki.text.toString(),
                edit_password_dangki.text.toString(),
                edit_ho_ten_dangki.text.toString(),
                edt_diachi_dangki.text.toString(),
                edt_sodienthoai_dangki.text.toString(),
                edit_email_dangki.text.toString(),
                radioButton.text.toString(),
                1
            )
            val service =
                RetrofitClientInstance().getClientSach()?.create(GetDataService::class.java)
            val call = service?.registerUser(registerModel)
            call?.enqueue(object : Callback<RegisterResponse> {
                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful)
                    {
                        edit_email_dangki.text.clear()
                        edit_password_dangki.text.clear()
                        edit_ho_ten_dangki.text.clear()
                        edt_diachi_dangki.text.clear()
                        edt_sodienthoai_dangki.text.clear()
                        Toast.makeText(activity,response.body()!!.message,Toast.LENGTH_LONG).show()
                        loadFragment(ProfileFragment())
                    }
                    else
                    {
                        Toast.makeText(activity,"Đăng kí thất bại",Toast.LENGTH_LONG).show()
                    }
                }
            })
        }

        return view
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
    fun newInstance(): RegisterFragment {
        val recipeTabFragment =
            RegisterFragment()
        return recipeTabFragment
    }
}
