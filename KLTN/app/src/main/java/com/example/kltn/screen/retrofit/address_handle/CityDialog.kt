package com.example.kltn.screen.retrofit.address_handle

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.R
import com.example.kltn.screen.retrofit.GetDataService
import com.example.kltn.screen.retrofit.RetrofitClientInstance
import com.example.kltn.screen.retrofit.model.CityAdapter
import com.example.kltn.screen.retrofit.model.CityModel
import com.example.kltn.screen.retrofit.reponse.CityResponse
import com.example.kltn.screen.retrofit.reponse.DistrictResponse
import com.example.kltn.screen.retrofit.reponse.WardResponse
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CityDialog(val type: Int,val idTitle: Int) : DialogFragment() {
    lateinit var recyclerviewCity: RecyclerView
    lateinit var cityAdapter: CityAdapter
    lateinit var tv_huy: TextView
    lateinit var tv_title_location: TextView
    private var onActionData: OnActionData<CityModel>? = null

    interface OnInputSelected {
        fun sendInput(cityModel: CityModel,type: Int)
    }

    var mOnInputSelected: OnInputSelected? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialogfragment_city, container, false)
        recyclerviewCity = view.findViewById(R.id.recyclerview_city)
        tv_title_location = view.findViewById(R.id.title_location)
        recyclerviewCity.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        tv_huy = view.findViewById(R.id.tv_huy)
        tv_huy.setOnClickListener {
            dismiss()
        }
        if (type == 1 ) {
            tv_title_location.text = "Thành phố*"
            loadListCity()
        }
        else if (type == 2)
        {
            tv_title_location.text = "Quận Huyện*"
            loadListDistrict(idTitle)
        }
        else
        {
            tv_title_location.text = "Phường Xã"
            loadListWard(idTitle)
        }
        return view
    }

    private fun loadListCity() {
        val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)
        val call = service?.getListCity()
        call?.enqueue(object : Callback<CityResponse> {
            override fun onFailure(call: Call<CityResponse>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<CityResponse>,
                response: Response<CityResponse>
            ) {
                Log.d("ThangTruong", response.body().toString())
                setUpRecyclerviewCity(response)
            }
        })
    }

    private fun loadListDistrict(idTitle: Int) {
        val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)
        val call = service?.getListDistrict(idTitle)
        call?.enqueue(object : Callback<List<DistrictResponse>> {
            override fun onFailure(call: Call<List<DistrictResponse>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<DistrictResponse>>,
                response: Response<List<DistrictResponse>>
            ) {
                Log.d("ThangTruong", response.body().toString())
                setUpRecyclerviewDistrict(response)
            }
        })
    }

    private fun loadListWard(idTitle: Int) {
        val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)
        val call = service?.getListWard(idTitle)
        call?.enqueue(object : Callback<List<WardResponse>> {
            override fun onFailure(call: Call<List<WardResponse>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<WardResponse>>,
                response: Response<List<WardResponse>>
            ) {
                Log.d("ThangTruong", response.body().toString())
                setUpRecyclerviewWard(response)
            }
        })
    }

    fun setUpRecyclerviewWard(response: Response<List<WardResponse>>) {
        val arrayListWard = ArrayList<CityModel>()
        response.body()!!.forEach {
            arrayListWard.add(CityModel(it.iD, it.title))
        }
        onActionData = object : OnActionData<CityModel> {
            override fun onAction(data: CityModel) {
                //handleDialog?.sendDataToFragment(data)
                mOnInputSelected?.sendInput(data,3);
//                val bundle = Bundle()
//                bundle.putParcelable("cityModel",data)
//                addressFragment.arguments = bundle
                dismiss()
            }
        }
        cityAdapter = CityAdapter(arrayListWard, onActionData!!)
        recyclerviewCity.adapter = cityAdapter
    }

    fun setUpRecyclerviewCity(response: Response<CityResponse>) {
        val arrayListCity = ArrayList<CityModel>()
        response.body()!!.ltsItem.forEach {
            arrayListCity.add(CityModel(it.iD, it.title))
        }
        onActionData = object : OnActionData<CityModel> {
            override fun onAction(data: CityModel) {
                //handleDialog?.sendDataToFragment(data)
                mOnInputSelected?.sendInput(data,1);
//                val bundle = Bundle()
//                bundle.putParcelable("cityModel",data)
//                addressFragment.arguments = bundle
                dismiss()
            }
        }
        cityAdapter = CityAdapter(arrayListCity, onActionData!!)
        recyclerviewCity.adapter = cityAdapter
    }

    fun setUpRecyclerviewDistrict(response: Response<List<DistrictResponse>>)
    {
        val arrayListDistrict = ArrayList<CityModel>()
        response.body()!!.forEach {
            arrayListDistrict.add(CityModel(it.iD, it.title))
        }
        onActionData = object : OnActionData<CityModel> {
            override fun onAction(data: CityModel) {
                //handleDialog?.sendDataToFragment(data)
                mOnInputSelected?.sendInput(data,2);
//                val bundle = Bundle()
//                bundle.putParcelable("cityModel",data)
//                addressFragment.arguments = bundle
                dismiss()
            }
        }
        cityAdapter = CityAdapter(arrayListDistrict, onActionData!!)
        recyclerviewCity.adapter = cityAdapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mOnInputSelected = targetFragment as OnInputSelected?
        } catch (e: ClassCastException) {
            Log.e("ThangTruong", "onAttach: ClassCastException : " + e.message)
        }
    }
}