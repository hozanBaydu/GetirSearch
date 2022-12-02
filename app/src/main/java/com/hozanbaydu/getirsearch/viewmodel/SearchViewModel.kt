package com.hozanbaydu.getirsearch.viewmodel

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hozanbaydu.getirsearch.firebase.GetData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(

    private val getFromFirebase: GetData

):ViewModel(){

     fun getFood(foodList: MutableLiveData<MutableList<String>>,context: Context){
        getFromFirebase.getData(foodList,context)
    }

    fun resultData(restaurantNameText: TextView, foodNameTextView: TextView, priceTextView: TextView, id:String, context: Context,imageView: ImageView){

        getFromFirebase.resultData(restaurantNameText,foodNameTextView,priceTextView,id,context,imageView)
    }



    val dataList= MutableLiveData<MutableList<String>>()
    var foodArray=getFromFirebase.foodArray
    var idArray=getFromFirebase.idArray
    var imortanceist=ArrayList<Int>()
    var minFood=String()

}