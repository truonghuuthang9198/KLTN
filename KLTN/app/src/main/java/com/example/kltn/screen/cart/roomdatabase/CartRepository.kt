package com.example.kltn.screen.cart.roomdatabase

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.kltn.screen.cart.model.CartModel

class  CartRepository(private val cartDao: CartDao){
    val listCart: LiveData<List<CartModel>> = cartDao.getListCart()


    fun insertItemCart(cartModel: CartModel)
    {
        cartDao.insertItemCart(cartModel)
    }
    fun deleteItemCart(cartModel: CartModel)
    {
        cartDao.deleteItemCart(cartModel)
    }
    fun getList():List<CartModel>
    {
        return cartDao.getList()
    }
}