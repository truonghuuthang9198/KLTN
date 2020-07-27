package com.example.kltn.screen.suggest.roomsuggest

import androidx.lifecycle.LiveData
import com.example.kltn.screen.cart.model.CartModel
import com.example.kltn.screen.cart.roomcart.CartDao
import com.example.kltn.screen.suggest.model.SuggestModel

class  SuggestRepository(private val suggestDao: SuggestDao){

    fun insertItemSuggest(suggestModel: SuggestModel)
    {
        suggestDao.insertItemSuggest(suggestModel)
    }
    fun deleteItemSuggest(suggestModel: SuggestModel)
    {
        suggestDao.deleteItemSuggest(suggestModel)
    }
    fun getList():List<SuggestModel>
    {
        return suggestDao.getList()
    }
    fun countKeySuggest(): Int
    {
        return suggestDao.countKeySuggest()
    }
    fun deleteAll()
    {
        return suggestDao.deleteAll()
    }
    fun checkSuggestList(pMaTL:String): SuggestModel
    {
        return suggestDao.checkExistList(pMaTL)
    }
}