package com.example.kltn.screen.profile



import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.kltn.R
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import java.util.*


@Suppress("DEPRECATION")
class LoginFragment : Fragment() {
    private var callBackManager: CallbackManager? = null
    private var auth: FirebaseAuth? = null
    lateinit var btnDangNhap: Button

    lateinit var btnDangNhapFB: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        btnDangNhap = view.findViewById(R.id.btn_dangnhap)
        btnDangNhapFB = view.findViewById(R.id.btn_dangnhap_fb)

        callBackManager = CallbackManager.Factory.create()
        btnDangNhap.setOnClickListener {
            loadFragment(InformationFragment())
            val pref = PreferenceManager.getDefaultSharedPreferences(activity!!)
            val edit = pref.edit()
            edit.putBoolean("CheckLogin",true)
            edit.apply()
        }
        btnDangNhapFB.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(activity!!,Arrays.asList("public_profile","email"))
            LoginManager.getInstance().registerCallback(callBackManager,object : FacebookCallback<LoginResult>{
                override fun onSuccess(result: LoginResult?) {
                    handleFacebookAccessToken(result?.accessToken)
                    Toast.makeText(activity!!,result!!.toString(),Toast.LENGTH_LONG).show()

                }

                override fun onCancel() {
                    TODO("Not yet implemented")
                }

                override fun onError(error: FacebookException?) {
                    TODO("Not yet implemented")
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
                loadFragment(InformationFragment())
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callBackManager?.onActivityResult(requestCode,resultCode,data)
    }
    fun newInstance(): LoginFragment {
        val recipeTabFragment =
            LoginFragment()
        return recipeTabFragment
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
