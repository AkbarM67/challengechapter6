package com.example.challengechapter6.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengechapter6.model.DataBuku
import com.example.challengechapter6.model.ResponDataBukuItem
import com.example.challengechapter6.model.putResponseBuku
import com.example.challengechapter6.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelBuku: ViewModel() {

    lateinit var allData : MutableLiveData<List<ResponDataBukuItem>>
    lateinit var updateBuku: MutableLiveData<List<putResponseBuku>?>
    lateinit var deleteBuku: MutableLiveData<Int?>
    lateinit var postBuku: MutableLiveData<ResponDataBukuItem>

    init {
        allData = MutableLiveData()
        updateBuku = MutableLiveData()
        deleteBuku = MutableLiveData()
        postBuku = MutableLiveData()
    }

    fun allLiveData() : MutableLiveData<List<ResponDataBukuItem>>{
        return allData
    }

    fun deleteLiveDataBuku(): MutableLiveData<Int?>{
        return deleteBuku
    }

    fun updateLiveDataBuku() :MutableLiveData<List<putResponseBuku>?>{
        return updateBuku
    }

    fun addLiveData(): MutableLiveData<ResponDataBukuItem>{
        return postBuku
    }

    fun callAllData(){
        ApiClient.instance.getAllUSer()
            .enqueue(object : Callback<List<ResponDataBukuItem>>{
                override fun onResponse(
                    call: Call<List<ResponDataBukuItem>>,
                    response: Response<List<ResponDataBukuItem>>
                ) {
                    if (response.isSuccessful){
                        allData.postValue(response.body())
                    }else{
                        error(response.message())
                    }
                }

                override fun onFailure(call: Call<List<ResponDataBukuItem>>, t: Throwable) {
                    allData.postValue(error(t.message.toString()))
                }

            })
    }

    fun updateApiBuku(id: Int, judul: String, deskripsi: String,img: String,tahun: String){
        ApiClient.instance.updateBuku(id, DataBuku(id,judul,deskripsi,img,tahun))
            .enqueue(object : Callback<List<putResponseBuku>>{
                override fun onResponse(
                    call: Call<List<putResponseBuku>>,
                    response: Response<List<putResponseBuku>>
                ) {
                    if (response.isSuccessful){
                        updateBuku.postValue(response.body())
                    }else{
                        updateBuku.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<putResponseBuku>>, t: Throwable) {
                    updateBuku.postValue(null)
                }

            })
    }

    fun deleteApiBuku(id: Int){
        ApiClient.instance.deleteBuku(id)
            .enqueue(object : Callback<Int>{
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful){
                        deleteBuku.postValue(response.body())
                    }else{
                        Log.i("Detele", "onResponse: ")
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    deleteBuku.postValue(null)
                }

            })
    }

    fun postData(deskripsi: String, judul: String, img: String,tahun: String){
        ApiClient.instance.addData(ResponDataBukuItem("",deskripsi,"",img,tahun,judul))
            .enqueue(object : Callback<ResponDataBukuItem>{
                override fun onResponse(
                    call: Call<ResponDataBukuItem>,
                    response: Response<ResponDataBukuItem>
                ) {
                    if (response.isSuccessful){
                        postBuku.postValue(response.body())
                    }else{
                        //error(response.message())
                        Log.d("onResponse", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<ResponDataBukuItem>, t: Throwable) {
                    postBuku.postValue(error(t.message.toString()))
                }

            })
    }
}