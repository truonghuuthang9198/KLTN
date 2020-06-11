package com.example.kltn.screen.cart.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kltn.screen.cart.model.CartModel

@Dao
interface CartDao {
    @Query ("select * from cart_table")
    fun getListCart():LiveData<List<CartModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItemCart(cartModel: CartModel)

    @Delete
    fun deleteItemCart(cartModel: CartModel)
}