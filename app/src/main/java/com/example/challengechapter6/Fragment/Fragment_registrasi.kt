package com.example.challengechapter6.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.challengechapter6.R
import com.example.challengechapter6.Room.BukuDataBase
import com.example.challengechapter6.Room.UserAccount
import com.example.challengechapter6.databinding.FragmentLoginBinding
import com.example.challengechapter6.databinding.FragmentRegistrasiBinding
import com.example.challengechapter6.viewModel.ViewModelUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Fragment_registrasi : Fragment() {

    private var _binding: FragmentRegistrasiBinding? = null
    private val binding get() = _binding!!
    private var mDb: BukuDataBase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _binding = FragmentRegistrasiBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mDb = BukuDataBase.getInstance(requireContext())

        binding.btnRegistrasi.setOnClickListener {
            val name = binding.etName.text.toString()
            val username = binding.etUsername.toString()
            val password = binding.etPassword.text.toString()
            val confirpassword = binding.etConfirmPassword.text.toString()

           val regist = UserAccount(null, username,password)
            when {
                name.isNullOrEmpty() -> {
                    binding.materialName.error = "kolom name harus diisi"
                }
                username.isNullOrEmpty() -> {
                    binding.materialUsername.error = "Kolom username harus diisi"
                }
                password.isNullOrEmpty() -> {
                    binding.materialPassword.error = "Kolom password harus diisi"
                }
                confirpassword.isNullOrEmpty() -> {
                    binding.materialConfirmPassword.error = "Kolom konfirmasi password harus diisi"
                }
                password.lowercase() != confirpassword.lowercase() -> {
                    binding.materialConfirmPassword.error = "Password dan konfirmasi password tidak sama"
                    binding.etConfirmPassword.setText("")
                } else -> {
                    lifecycleScope.launch(Dispatchers.IO){
                        val result = mDb?.UserAccountDao()?.insertUserAccount(regist)
                        activity?.runOnUiThread{
                            if (result != 0.toLong()){
                                Toast.makeText(context, "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(context, "Pendaftaran Gagal", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                Navigation.findNavController(view).navigate(R.id.action_fragment_registrasi_to_fragment_login)
                }
            }
            }
        binding.btnlogin.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_fragment_registrasi_to_fragment_login)
        }
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null }
    }