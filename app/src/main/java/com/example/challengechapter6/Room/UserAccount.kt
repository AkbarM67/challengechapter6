package com.example.challengechapter6.Room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class UserAccount (
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "username") var name: String?,
    @ColumnInfo(name = "password") var password: String?,
    @ColumnInfo(name = "fullName") var fullName: String? = null,
    @ColumnInfo(name = "birthDate") var birthDate: String? = null,
    @ColumnInfo(name = "address") var address: String? = null
): Parcelable