package com.example.challengechapter6

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.challengechapter6.databinding.FragmentDialogDeleteBinding
import com.example.challengechapter6.viewModel.ViewModelBuku


class Fragment_dialogDelete : Fragment() {

    lateinit var binding: FragmentDialogDeleteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDialogDeleteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnHapus.setOnClickListener {
            requireActivity().run {
                var ID = arguments?.getInt("delete",0)
                Log.d("infoid", ID.toString())
                deleteBuku(ID!!.toInt())
                Navigation.findNavController(it).navigate(R.id.action_fragment_dialogDelete_to_fragment_Home)
            }
        }

        binding.btnBatal.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_fragment_dialogDelete_to_fragment_Home)
        }
    }

    fun deleteBuku(id: Int){
        val viewModel = ViewModelProvider(this).get(ViewModelBuku::class.java)
        viewModel.deleteApiBuku(id)
        viewModel.deleteLiveDataBuku().observe(viewLifecycleOwner, Observer {
            if (it != null){
                Toast.makeText(context, "Update Data Success", Toast.LENGTH_SHORT).show()
                Log.d("deleteBuku:", it.toString())
            }
        })
    }

}