package com.example.kltn.screen.cart.roomcart

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
    fun updateSL(pMaSach: String, pSoLuong:Int)
    {
        cartDao.updateSL(pMaSach,pSoLuong)
    }
    fun checkExistList(pMaSach: String):CartModel
    {
        return cartDao.checkExistList(pMaSach)
    }
    fun deleteAll()
    {
        return cartDao.deleteAll()
    }
}