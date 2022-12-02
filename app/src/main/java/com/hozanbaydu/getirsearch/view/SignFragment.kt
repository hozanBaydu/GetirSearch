package com.hozanbaydu.getirsearch.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hozanbaydu.getirsearch.R
import com.hozanbaydu.getirsearch.databinding.FragmentSearchBinding
import com.hozanbaydu.getirsearch.databinding.FragmentSignBinding

class SignFragment:Fragment(R.layout.fragment_sign) {

    private lateinit var auth: FirebaseAuth
    private var fragmentBinding : FragmentSignBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSignBinding.bind(view)
        fragmentBinding = binding


        auth = Firebase.auth

        val currentUsers=auth.currentUser  //initialize ettik.

        if (currentUsers!=null){
            findNavController().navigate(SignFragmentDirections.actionSignFragmentToMainFragment())
        }

        binding.signIn.setOnClickListener {
            val email=binding.eMail.text.toString()
            val password=binding.passsword.text.toString()

            if (email.equals("") || password.equals("")){

                Toast.makeText(requireContext(),"Enter your email and password!!", Toast.LENGTH_LONG).show()

            }else{
                auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                    findNavController().navigate(SignFragmentDirections.actionSignFragmentToMainFragment())

                }.addOnFailureListener {
                    Toast.makeText(requireContext(),it.localizedMessage, Toast.LENGTH_LONG).show() //localizedMessage Firebasenin kendi hata mesajlarını gostermek için.
                }
            }
        }

        binding.signUp.setOnClickListener {
            val email=binding.eMail.text.toString()
            val password=binding.passsword.text.toString()

            if (email.equals("") || password.equals("")){

                Toast.makeText(requireContext(),"Enter your email and password!!",Toast.LENGTH_LONG).show()

            }else{
                auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                    findNavController().navigate(SignFragmentDirections.actionSignFragmentToMainFragment())

                }.addOnFailureListener {
                    Toast.makeText(requireContext(),it.localizedMessage,Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}