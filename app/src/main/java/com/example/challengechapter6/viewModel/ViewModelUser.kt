package com.example.challengechapter6.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengechapter6.model.RespondeDataUser
import com.example.challengechapter6.model.RespondeDataUserItem
import com.example.challengechapter6.network.ApiClient
import com.example.challengechapter6.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelUser: ViewModel() {

    lateinit var posApiUser: MutableLiveData<RespondeDataUserItem?>
    lateinit var updateUser: MutableLiveData<RespondeDataUserItem?>

    init {
        posApiUser = MutableLiveData()
        updateUser = MutableLiveData()
    }

    fun postLiveDataUser() : MutableLiveData<RespondeDataUserItem?>{
        return posApiUser
    }

    fun updateLiveDataUser() :MutableLiveData<RespondeDataUserItem?>{
        return updateUser
    }

    fun posApiUser(name:String, password:String ,username: String){
        ApiClient.instance.postUser(RespondeDataUserItem("",name,password,username))
            .enqueue(object : Callback<RespondeDataUserItem>{
                override fun onResponse(
                    call: Call<RespondeDataUserItem>,
                    response: Response<RespondeDataUserItem>
                ) {
                    if (response.isSuccessful){
                        posApiUser.postValue(response.body())
                    }else{
                        error(response.message())
                    }
                }

                override fun onFailure(call: Call<RespondeDataUserItem>, t: Throwable) {
                    posApiUser.postValue(null)
                }

            })
    }

    fun putUser(id:String,name: String, username: String, password: String){
        ApiClient.instance.postUser(RespondeDataUserItem(id,name,password,username))
            .enqueue(object : Callback<RespondeDataUserItem>{
                override fun onResponse(
                    call: Call<RespondeDataUserItem>,
                    response: Response<RespondeDataUserItem>
                ) {
                    if (response.isSuccessful){
                        updateUser.postValue(response.body())
                    }else{
                        error(response.message())
                    }
                }

                override fun onFailure(call: Call<RespondeDataUserItem>, t: Throwable) {
                    updateUser.postValue(null)
                }

            })
    }
}