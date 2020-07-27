package com.example.kltn.screen.suggest.roomsuggest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kltn.screen.cart.model.CartModel
import com.example.kltn.screen.cart.roomcart.CartDao
import com.example.kltn.screen.suggest.model.SuggestModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [SuggestModel::class], version = 2,exportSchema = false)
abstract class SuggestRoomDB : RoomDatabase() {
    abstract fun suggestDao(): SuggestDao
    companion object {
        @Volatile
        private var INSTANCE: SuggestRoomDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): SuggestRoomDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SuggestRoomDB::class.java,
                    "suggest_database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().addCallback(SuggestRoomDBCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }

        private class SuggestRoomDBCallback(private val scope: CoroutineScope) :
            RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        database.suggestDao()
                    }
                }
            }
        }
    }
}