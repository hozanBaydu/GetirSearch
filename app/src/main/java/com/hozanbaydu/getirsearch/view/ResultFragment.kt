package com.hozanbaydu.getirsearch.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hozanbaydu.getirsearch.R
import com.hozanbaydu.getirsearch.databinding.FragmentResultBinding

import com.hozanbaydu.getirsearch.viewmodel.SearchViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ResultFragment:Fragment(R.layout.fragment_result) {

    private var fragmentBinding : FragmentResultBinding? = null
    lateinit var viewModel: SearchViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

        val binding = FragmentResultBinding.bind(view)
        fragmentBinding = binding

        binding.homeButton.setOnClickListener {
            findNavController().navigate(ResultFragmentDirections.actionResultFragmentToMainFragment())

        }



        lifecycleScope.launch {
            var a=viewModel.getFood(viewModel.dataList,requireContext())
            delay(200)
            var foods=viewModel.idArray
            if (foods.isNotEmpty()){

                var food0=foods.count{
                     it=="0000"
                }
                var food1=foods.count{
                    it=="0001"
                }
                var food2=foods.count{
                    it=="0002"
                }
                var food3=foods.count{
                    it=="0003"
                }
                var food4=foods.count{
                    it=="0004"
                }
                var food5=foods.count{
                    it=="0005"
                }
                val numbersMap = mapOf("0000" to food0, "0001" to food1, "0002" to food2,"0003" to food3,"0004" to food4,"0005" to food5)
                val filteredFood=numbersMap.filterValues {
                    it==4
                }
                var listFood=filteredFood.keys.toList()

                println(listFood.size)

                launch {
                    delay(200)

                    if (listFood.size==3 ){

                        binding.layout1.visibility=View.VISIBLE
                        binding.layout2.visibility=View.VISIBLE
                        binding.layout3.visibility=View.VISIBLE

                        viewModel.resultData(binding.restaurantText,binding.foodText,binding.priceText,listFood.get(0),requireContext(),binding.imageview1)
                        viewModel.resultData(binding.restaurantText2,binding.foodText2,binding.priceText2,listFood.get(1),requireContext(),binding.imageView2)
                        viewModel.resultData(binding.restaurantText3,binding.foodText3,binding.priceText3,listFood.get(2),requireContext(),binding.imageview3)

                        binding.checkText.visibility=View.VISIBLE
                        binding.checkText2.visibility=View.VISIBLE
                        binding.checkText.text="%100"
                        binding.checkText2.text="%100"
                    }

                    else if (listFood.size==2){

                        binding.layout1.visibility=View.VISIBLE
                        binding.layout2.visibility=View.VISIBLE


                        viewModel.resultData(binding.restaurantText,binding.foodText,binding.priceText,listFood.get(0),requireContext(),binding.imageview1)
                        viewModel.resultData(binding.restaurantText2,binding.foodText2,binding.priceText2,listFood.get(1),requireContext(),binding.imageView2)

                        binding.checkText.visibility=View.VISIBLE
                        binding.checkText2.visibility=View.VISIBLE
                        binding.checkText.text="%100"
                        binding.checkText2.text="%100"


                        viewModel.dataList.value?.remove(viewModel.minFood)
                        var a=viewModel.getFood(viewModel.dataList,requireContext())

                        delay(200)
                        var foodsb=viewModel.idArray
                        if (foods.isNotEmpty()){

                            var food0b=foods.count{
                                it=="0000"
                            }
                            var food1b=foods.count{
                                it=="0001"
                            }
                            var food2b=foods.count{
                                it=="0002"
                            }
                            var food3b=foods.count{
                                it=="0003"
                            }
                            var food4b=foods.count{
                                it=="0004"
                            }
                            var food5b=foods.count{
                                it=="0005"
                            }
                            val numbersMapb = mapOf("0000" to food0b, "0001" to food1b, "0002" to food2b,"0003" to food3b,"0004" to food4b,"0005" to food5b)
                            val filteredFoodb=numbersMapb.filterValues {
                                it==3
                            }
                            var listFoodb=filteredFoodb.keys.toList()

                            val difference = listFoodb.toSet().minus(listFood.toSet()).toList()




                            if (listFoodb.size>2){

                            binding.layout3.visibility=View.VISIBLE

                            binding.checkText3.visibility=View.VISIBLE
                            viewModel.resultData(binding.restaurantText3,binding.foodText3,binding.priceText3,difference.get(0),requireContext(),binding.imageview3)
                            binding.checkText3.text="%75"
                            }


                    }

                    else if (listFood.size==1){
                        viewModel.resultData(binding.restaurantText,binding.foodText,binding.priceText,listFood.get(0),requireContext(),binding.imageview1)

                    }


                }
            }
        }
    }
}
    }
