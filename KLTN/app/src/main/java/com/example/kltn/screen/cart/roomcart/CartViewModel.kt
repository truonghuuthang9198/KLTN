package com.example.kltn.screen.cart.roomcart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kltn.screen.cart.model.CartModel
import kotlinx.coroutines.*

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

    fun updateSL(pMaSach: String,pSoLuong: Int) = viewModelScope.launch(Dispatchers.IO) {
        cartRepository.updateSL(pMaSach,pSoLuong)
    }

    fun getList():List<CartModel>
    {
        return cartRepository.getList()
    }

    fun checkExistList(pMaSach: String):CartModel
    {
        return cartRepository.checkExistList(pMaSach)
    }

}