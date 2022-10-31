package com.example.challengechapter6.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserAccountDao {
    @Update
    fun updateAccount(user: UserAccount):Int

    @Query("SELECT * FROM UserAccount WHERE username = :username AND password = :password")
    fun login(username: String, password:String):UserAccount?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserAccount(user: UserAccount):Long
}