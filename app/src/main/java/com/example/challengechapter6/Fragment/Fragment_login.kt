package com.example.challengechapter6.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.challengechapter6.MainActivity
import com.example.challengechapter6.R
import com.example.challengechapter6.Room.BukuDataBase
import com.example.challengechapter6.databinding.FragmentLoginBinding
import com.example.challengechapter6.model.RespondeDataUserItem
import com.example.challengechapter6.network.ApiClient
import com.example.challengechapter6.viewModel.ViewModelUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.cache2.Relay.Companion.edit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Fragment_login : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var mDb: BukuDataBase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mDb = BukuDataBase.getInstance(requireContext())
        val sharedPreferences = requireContext().getSharedPreferences(MainActivity.SHARED_PREFERENCES, Context.MODE_PRIVATE)

        binding.btnlogin.setOnClickListener {
            val inputusername = binding.edituserName.text.toString()
            val inputpassword = binding.editpassword.text.toString()

            lifecycleScope.launch(Dispatchers.IO){
                val login = mDb?.UserAccountDao()?.login(inputusername,inputpassword)
                activity?.runOnUiThread{
                    if (login == null){
                        Toast.makeText(context, "username atau password anda salah", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        val editor = sharedPreferences.edit()
                        editor.putString("username", inputusername)
                        editor.putString("password", inputpassword)
                        editor.apply()
                        Navigation.findNavController(view).navigate(R.id.action_fragment_login_to_fragment_Home)
                    }
                }
            }
        }
        binding.btnRegistrasi.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragment_login_to_fragment_registrasi)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    }