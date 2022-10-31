package com.example.challengechapter6.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.telecom.Call
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengechapter6.R
import com.example.challengechapter6.adapterBuku
import com.example.challengechapter6.databinding.FragmentHomeBinding
import com.example.challengechapter6.model.ResponDataBukuItem
import com.example.challengechapter6.network.ApiClient
import com.example.challengechapter6.viewModel.ViewModelBuku
import retrofit2.Callback
import retrofit2.Response


class Fragment_Home : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var adapterBuku : adapterBuku
    lateinit var modelBuku: ViewModelBuku
    lateinit var datauserShared : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showList()
        datauserShared = requireActivity().getSharedPreferences("datauser", Context.MODE_PRIVATE)

        modelBuku = ViewModelProvider(this).get(ViewModelBuku::class.java)
        modelBuku.allLiveData().observe(viewLifecycleOwner, Observer {
            adapterBuku.setData(it as ArrayList<ResponDataBukuItem>)
        })

        adapterBuku = adapterBuku(ArrayList())
        _binding!!.rvBuku.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        _binding!!.rvBuku.adapter = adapterBuku

        _binding!!.fbAddBuku.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_Home_to_fragment_add)
        }
        _binding!!.btnlogout.setOnClickListener{
            clearData()
            Toast.makeText(context, "logout berhasil", Toast.LENGTH_SHORT).show()
            gotoLogin()
        }
        _binding!!.btnProfil.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_Home_to_fragment_Profil)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null!!
    }

    private fun showList(){
        ApiClient.instance.getAll()
            .enqueue(object : Callback<List<ResponDataBukuItem>>{
                override fun onResponse(
                    call: retrofit2.Call<List<ResponDataBukuItem>>,
                    response: Response<List<ResponDataBukuItem>>
                ) {
                    if (response.isSuccessful){
                        _binding!!.rvBuku.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        _binding!!.rvBuku.adapter = adapterBuku(response.body()!!)
                        Toast.makeText(context, "load data berhasil", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(context, "load data gagal", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(
                    call: retrofit2.Call<List<ResponDataBukuItem>>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show()
                }

            })
    }

    fun clearData(){
        var pref = datauserShared.edit()
        pref.clear()
        pref.apply()
    }

    fun gotoLogin(){
        Navigation.findNavController(requireView()).navigate(R.id.action_fragment_Home_to_fragment_login)
    }

}