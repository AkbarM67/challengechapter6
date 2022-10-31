package com.example.challengechapter6.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengechapter6.model.ResponDataBukuItem
import com.example.challengechapter6.model.putResponseBuku
import com.example.challengechapter6.network.ApiClient
import com.example.challengechapter6.viewModel.ViewModelBuku
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class mainViewBuku: ViewModel() {
    private val _code = MutableLiveData<Int>()
    val code : LiveData<Int>
    get() = _code

    private val _Buku = MutableLiveData<List<putResponseBuku>>()
    val Buku: LiveData<List<putResponseBuku>>
    get() = _Buku

    init {
        loadBuku()
    }

    private fun loadBuku(){
        ApiClient.instance.getAllUSer()
            .enqueue(object : Callback<ResponDataBukuItem>{
                override fun onResponse(
                    call: Call<ResponDataBukuItem>,
                    response: Response<ResponDataBukuItem>
                ) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<ResponDataBukuItem>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}