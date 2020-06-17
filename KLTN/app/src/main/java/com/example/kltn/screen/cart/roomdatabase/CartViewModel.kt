package com.example.kltn.screen.cart.roomdatabase

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kltn.screen.cart.model.CartModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CartViewModel(application: Application) : AndroidViewModel(application) {
    private val cartRepository: CartRepository
    val listCart: LiveData<List<CartModel>>

    init {
        val cartDao = CartRoomDB.getDatabase(application,viewModelScope).cartDao()
        cartRepository= CartRepository(cartDao)
        listCart = cartRepository.listCart
    }

    fun insertItemCart(cartModel: CartModel) = viewModelScope.launch(Dispatchers.IO) {
        cartRepository.insertItemCart(cartModel)
    }

    fun deleteItemCart(cartModel: CartModel) = viewModelScope.launch(Dispatchers.IO) {
        cartRepository.deleteItemCart(cartModel)
    }

    fun updateSL(pTenSach: String,pSoLuong: Int) = viewModelScope.launch(Dispatchers.IO) {
        cartRepository.updateSL(pTenSach,pSoLuong)
    }

    fun getList():List<CartModel>
    {
        return cartRepository.getList()
    }

    fun checkExistList(pTenSach: String):CartModel
    {
        return cartRepository.checkExistList(pTenSach)
    }

}