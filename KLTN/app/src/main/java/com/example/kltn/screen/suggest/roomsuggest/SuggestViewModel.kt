package com.example.kltn.screen.suggest.roomsuggest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kltn.screen.cart.model.CartModel
import com.example.kltn.screen.cart.roomcart.CartRepository
import com.example.kltn.screen.cart.roomcart.CartRoomDB
import com.example.kltn.screen.suggest.model.SuggestModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuggestViewModel(application: Application) : AndroidViewModel(application) {
    private val suggestRepository: SuggestRepository

    init {
        val suggestDao = SuggestRoomDB.getDatabase(application,viewModelScope).suggestDao()
        suggestRepository= SuggestRepository(suggestDao)
    }

    fun insertItemSuggest(suggestModel: SuggestModel) = viewModelScope.launch(Dispatchers.IO) {
        suggestRepository.insertItemSuggest(suggestModel)
    }

    fun deleteItemSuggest(suggestModel: SuggestModel) = viewModelScope.launch(Dispatchers.IO) {
        suggestRepository.deleteItemSuggest(suggestModel)
    }

    fun countKeySuggest():Int
    {
        return suggestRepository.countKeySuggest()
    }

    fun getList():List<SuggestModel>
    {
        return suggestRepository.getList()
    }

    fun delelteAll()
    {
        return suggestRepository.deleteAll()
    }

    fun checkSuggestList(pMaTL:String):SuggestModel
    {
        return suggestRepository.checkSuggestList(pMaTL)
    }

}