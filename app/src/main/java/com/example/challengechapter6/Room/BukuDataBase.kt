package com.example.challengechapter6.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserAccount::class], version = 1)
abstract class BukuDataBase():RoomDatabase(){
    abstract fun UserAccountDao(): UserAccountDao


    companion object{
        private var INSTANCE: BukuDataBase? = null

        fun getInstance(context: Context): BukuDataBase?{
            if (INSTANCE == null){
                synchronized(BukuDataBase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BukuDataBase::class.java, "Buku.db").build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}