package com.hozanbaydu.getirsearch.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hozanbaydu.getirsearch.R
import com.hozanbaydu.getirsearch.databinding.FragmentMainBinding
import com.hozanbaydu.getirsearch.firebase.GetData

class MainFragment:Fragment(R.layout.fragment_main) {

    private var fragmentBinding : FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view)
        fragmentBinding = binding




        binding.fikirButton.setOnClickListener {

            findNavController().navigate(MainFragmentDirections.actionMainFragmentToSearchFragment())
        }


    }
}