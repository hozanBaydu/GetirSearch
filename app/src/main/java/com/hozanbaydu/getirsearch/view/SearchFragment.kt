package com.hozanbaydu.getirsearch.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hozanbaydu.getirsearch.R
import com.hozanbaydu.getirsearch.databinding.FragmentSearchBinding
import com.hozanbaydu.getirsearch.firebase.GetData
import com.hozanbaydu.getirsearch.viewmodel.SearchViewModel

class SearchFragment:Fragment(R.layout.fragment_search) {

    private var fragmentBinding : FragmentSearchBinding? = null
    lateinit var viewModel: SearchViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

        val binding = FragmentSearchBinding.bind(view)
        fragmentBinding = binding





        binding.searchFikir.setOnClickListener {


            var textView=binding.nameText.text.toString()
            var textView2= binding.nameText2.text.toString()
            var textView3= binding.nameText3.text.toString()
            var textView4= binding.nameText4.text.toString()




            var list:MutableList<String> = mutableListOf(textView,textView2,textView3,textView4)
            viewModel.dataList.postValue(list)

            var importanceArrayList=viewModel.imortanceist
            importanceArrayList.clear()



            importanceArrayList.add(0, binding.textView.text.toString().toInt())
            importanceArrayList.add(1, binding.textView2.text.toString().toInt())
            importanceArrayList.add(2, binding.textView3.text.toString().toInt())
            importanceArrayList.add(3, binding.textView4.text.toString().toInt())

            var a=  importanceArrayList.minOf {
                it
            }
            if (a==importanceArrayList.get(0)){
                viewModel.minFood=binding.nameText.text.toString()
            }
            else if (a==importanceArrayList.get(1)){
                viewModel.minFood=binding.nameText2.text.toString()
            }
            else if (a==importanceArrayList.get(2)){
                viewModel.minFood=binding.nameText3.text.toString()
            }
            else if (a==importanceArrayList.get(3)){
                viewModel.minFood=binding.nameText4.text.toString()
            }



            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToResultFragment())

        }

        binding.seekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
               binding.textView.text=p1.toString()
                if (p1 in 21..49){
                binding.textView.textSize= p1/2.toFloat()

                }else if (p1<21){
                    binding.textView.textSize=10f
                }else{
                    binding.textView.textSize=25f
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {


            }
            override fun onStopTrackingTouch(p0: SeekBar?) {



                if (binding.textView.text.toString().toInt() > 50){

                    binding.textView.setTextColor(Color.GREEN)
                }else{
                    binding.textView.setTextColor(Color.BLACK)

                }
            }
        })



        binding.seekBar2.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.textView2.text=p1.toString()
                if (p1 in 21..49){
                    binding.textView2.textSize= p1/2.toFloat()

                }else if (p1<21){
                    binding.textView2.textSize=10f
                }else{
                    binding.textView2.textSize=25f
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {

                if (binding.textView2.text.toString().toInt() > 50){

                    binding.textView2.setTextColor(Color.GREEN)
                }else{
                    binding.textView2.setTextColor(Color.BLACK)

                }
            }
        })
        binding.seekBar3.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.textView3.text=p1.toString()
                if (p1 in 21..49){
                    binding.textView3.textSize= p1/2.toFloat()

                }else if (p1<21){
                    binding.textView3.textSize=10f
                }else{
                    binding.textView3.textSize=25f
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {

                if (binding.textView3.text.toString().toInt() > 50){

                    binding.textView3.setTextColor(Color.GREEN)
                }else{
                    binding.textView3.setTextColor(Color.BLACK)

                }
            }
        })
        binding.seekBar4.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.textView4.text=p1.toString()
                if (p1 in 21..49){
                    binding.textView4.textSize= p1/2.toFloat()

                }else if (p1<21){
                    binding.textView4.textSize=10f
                }else{
                    binding.textView4.textSize=25f
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {

                if (binding.textView4.text.toString().toInt() > 50){

                    binding.textView4.setTextColor(Color.GREEN)
                }else{
                    binding.textView4.setTextColor(Color.BLACK)

                }
            }
        })






    }


}