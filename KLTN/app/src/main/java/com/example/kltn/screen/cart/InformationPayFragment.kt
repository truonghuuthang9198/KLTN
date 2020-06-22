package com.example.kltn.screen.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.kltn.R

class InformationPayFragment : Fragment() {
    lateinit var backButton: ImageView
    lateinit var btnThanhToan: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_information_pay, container, false)
        backButton = view.findViewById(R.id.btn_back_information_pay)
        btnThanhToan = view.findViewById(R.id.btn_thanhtoan_information_pay)
        btnThanhToan.setOnClickListener {
            loadFragment(CheckBillFragment(),"CheckBillFragment")
        }
        backButton.setOnClickListener {
            if (fragmentManager!!.backStackEntryCount > 0) {
                fragmentManager!!.popBackStack()
            }
        }
        return view
    }
    private fun loadFragment(fragment: Fragment?, tag: String): Boolean {
        if (fragment != null) {
            (context as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment, tag)
                .addToBackStack(null)
                .commit()
            return true
        }
        return false
    }
}