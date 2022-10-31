package com.example.challengechapter6

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.challengechapter6.databinding.FragmentProfilBinding
import com.example.challengechapter6.viewModel.ViewModelUser


class Fragment_Profil : Fragment() {

    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!
    lateinit var userViewModel: ViewModelUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = requireContext().getSharedPreferences("datauser", Context.MODE_PRIVATE)
        userViewModel =  ViewModelProvider(requireActivity())[ViewModelUser::class.java]

        _binding!!.btnLogout.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_Profil_to_fragment_Home)
        }
    }


}

