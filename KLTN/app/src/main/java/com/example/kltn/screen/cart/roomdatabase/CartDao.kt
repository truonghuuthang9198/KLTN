package com.example.kltn.screen.cart.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kltn.screen.cart.model.CartModel

@Dao
interface CartDao {
    @Query ("select * from cart_table")
    fun getListCart():LiveData<List<CartModel>>

    @Insert
    fun insertItemCart(cartModel: CartModel)

    @Delete
    fun deleteItemCart(cartModel: CartModel)

    @Query("DELETE FROM cart_table")
    fun deleteAll()

    @Query("select * from cart_table")
    fun getList():List<CartModel>

    @Query("update cart_table set soLuong= :pSoLuong where maSach = :pMaSach")
    fun updateSL(pMaSach: String,pSoLuong: Int)

    @Query("select * from cart_table where maSach= :pMaSach")
    fun checkExistList(pMaSach: String): CartModel

}