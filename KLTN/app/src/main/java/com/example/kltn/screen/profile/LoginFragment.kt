package com.example.kltn.screen.profile



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.kltn.R


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        //val button = view.findViewById<Button>(R.id.btn_dangnhap)

        return view

    }
    fun newInstance(): LoginFragment {
        val recipeTabFragment =
            LoginFragment()
        return recipeTabFragment
    }
}
