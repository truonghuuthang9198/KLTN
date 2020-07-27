package com.example.kltn.screen.cart.roomcart

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kltn.screen.cart.model.CartModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [CartModel::class], version = 3,exportSchema = false)
abstract class CartRoomDB : RoomDatabase() {
    abstract fun cartDao(): CartDao
    companion object {
        @Volatile
        private var INSTANCE: CartRoomDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CartRoomDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CartRoomDB::class.java,
                    "cart_database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().addCallback(CartRoomDBCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }

        private class CartRoomDBCallback(private val scope: CoroutineScope) :
            RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        database.cartDao()
                    }
                }
            }
        }
    }
}