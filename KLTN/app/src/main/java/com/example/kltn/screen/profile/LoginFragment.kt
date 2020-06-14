package com.example.kltn.screen.profile



import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.kltn.R


@Suppress("DEPRECATION")
class LoginFragment : Fragment() {
    lateinit var btnDangNhap: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        btnDangNhap = view.findViewById<Button>(R.id.btn_dangnhap)
        btnDangNhap.setOnClickListener {
            val pref = PreferenceManager.getDefaultSharedPreferences(activity!!)
            val edit = pref.edit()
            edit.putBoolean("CheckLogin",true)
            edit.apply()
            loadFragment(InformationFragment())
        }

        return view

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
