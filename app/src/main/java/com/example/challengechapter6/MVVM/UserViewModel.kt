package com.example.challengechapter6.MVVM

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengechapter6.Room.UserAccount

class UserViewModel: ViewModel() {
    var userlogin : MutableLiveData<UserAccount> = MutableLiveData()
    fun getUserAccount(user: UserAccount){
        userlogin.postValue(user)
    }
}