package com.example.kltn.screen.cart.roomdatabase

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kltn.screen.cart.model.CartModel
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {
    private val cartRepository: CartRepository
    val listCart: LiveData<List<CartModel>>

    init {
        val cartDao = CartRoomDB.getDatabase(application, viewModelScope).cartDao()
        cartRepository= CartRepository(cartDao)
        listCart = cartRepository.listCart
    }

    fun insertItemCart(cartModel: CartModel) = viewModelScope.launch {
        cartRepository.insertItemCart(cartModel)
    }

    fun deleteHistorySearch(cartModel: CartModel) = viewModelScope.launch {
        cartRepository.deleteItemCart(cartModel)
    }
}