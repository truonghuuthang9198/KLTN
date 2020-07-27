package com.example.kltn.screen.suggest.roomsuggest

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kltn.screen.cart.model.CartModel
import com.example.kltn.screen.suggest.model.SuggestModel

@Dao
interface SuggestDao {
    @Insert
    fun insertItemSuggest(suggestModel: SuggestModel)

    @Delete
    fun deleteItemSuggest(suggestModel: SuggestModel)

    @Query("DELETE FROM suggest_table")
    fun deleteAll()

    @Query("select * from suggest_table")
    fun getList():List<SuggestModel>

    @Query("select Count(*) from suggest_table")
    fun countKeySuggest():Int

    @Query("select * from suggest_table where maTL= :pMaTL")
    fun checkExistList(pMaTL: String): SuggestModel

}