package com.hozanbaydu.getirsearch.firebase


import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.sql.Timestamp
import javax.inject.Inject


class GetData @Inject constructor(

    val glide: RequestManager
){

    var foodArray= ArrayList<Model>()
    var idArray=ArrayList<String>()

    fun resultData(restaurantNameText: TextView,foodNameTextView: TextView,priceTextView: TextView,id:String,context: Context,imageView:ImageView){
        lateinit var db: FirebaseFirestore
        db= Firebase.firestore
        db.collection("yemekler").whereArrayContains("yemek",id).orderBy("date",
            Query.Direction.DESCENDING).addSnapshotListener { value, error ->

            if (error!=null){
                Toast.makeText(context,error.localizedMessage, Toast.LENGTH_LONG).show()
            }else{
                if (value!=null){

                    if(!value.isEmpty){
                        val document=value.documents



                        for (document in document){
                            val comment=document.get("yemek") as? ArrayList<*>

                            val isim=comment?.get(1) as String?
                            val fiyat=comment?.get(2) as String?
                            val url=comment?.get(3)  as String?
                            val urdl=comment?.get(4)  as String?

                            restaurantNameText.text=isim
                            foodNameTextView.text=fiyat
                            priceTextView.text=url+" Tl"
                            println(urdl)
                            glide.load(urdl).into(imageView)







                        }
                    }
                }
            }
        }

    }

     fun getData(a: MutableLiveData<MutableList<String>>,context: Context){

         foodArray.clear()
         idArray.clear()
         lateinit var db: FirebaseFirestore
         db= Firebase.firestore
         a.value?.let {
             db.collection("yemekler").whereArrayContains("yemek",it.get(0)).orderBy("date",
                 Query.Direction.DESCENDING).addSnapshotListener { value, error ->

                 if (error!=null){
                     Toast.makeText(context,error.localizedMessage, Toast.LENGTH_LONG).show()
                 }else{
                     if (value!=null){
                         if(!value.isEmpty){
                             val document=value.documents

                             for (document in document){
                                 val comment=document.get("yemek") as? ArrayList<*>

                                 val isim=comment?.get(0) as String?
                                 val fiyat=comment?.get(1) as String?
                                 val url=comment?.get(2)  as String?
                                 var id=comment?.get(0) as String?

                                 var yemek=Model(isim,fiyat,url)
                                 foodArray.add(yemek)

                                 if (id != null) {
                                     idArray.add(id)


                                 }



                             }
                         }
                     }
                 }
             }


                 db.collection("yemekler").whereArrayContains("yemek", it.get(1)).orderBy(
                     "date",
                     Query.Direction.DESCENDING
                 ).addSnapshotListener { value, error ->

                     if (error != null) {
                         Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG).show()
                     } else {
                         if (value != null) {
                             if (!value.isEmpty) {
                                 val document = value.documents

                                 for (document in document) {
                                     val comment = document.get("yemek") as? ArrayList<*>

                                     val isim = comment?.get(0) as String
                                     val fiyat = comment?.get(1) as String
                                     val url = comment.get(2) as String
                                     var id = comment.get(0) as String

                                     var yemek = Model(isim, fiyat, url)
                                     foodArray.add(yemek)
                                     idArray.add(id)
                                 }
                             }
                         }
                     }
                 }

             db.collection("yemekler").whereArrayContains("yemek",it.get(2)).orderBy("date",
                 Query.Direction.DESCENDING).addSnapshotListener { value, error ->

                 if (error!=null){
                     Toast.makeText(context,error.localizedMessage, Toast.LENGTH_LONG).show()
                 }else{
                     if (value!=null){
                         if(!value.isEmpty){
                             val document=value.documents

                             for (document in document){
                                 val comment=document.get("yemek") as? ArrayList<*>

                                 val isim=comment?.get(0) as String
                                 val fiyat=comment?.get(1) as String
                                 val url=comment.get(2)  as String
                                 var id=comment.get(0) as String

                                 var yemek=Model(isim,fiyat,url)
                                 foodArray.add(yemek)
                                 idArray.add(id)

                             }
                         }
                     }
                 }
             }

             if (a.value!!.size>3) {
             db.collection("yemekler").whereArrayContains("yemek",it.get(3)).orderBy("date",
                 Query.Direction.DESCENDING).addSnapshotListener { value, error ->

                 if (error!=null){
                     Toast.makeText(context,error.localizedMessage, Toast.LENGTH_LONG).show()
                 }else{
                     if (value!=null){
                         if(!value.isEmpty){
                             val document=value.documents

                             for (document in document){
                                 val comment=document.get("yemek") as? ArrayList<*>

                                 val isim=comment?.get(0) as String
                                 val fiyat=comment?.get(1) as String
                                 val url=comment.get(2)  as String
                                 var id=comment.get(0) as String

                                 var yemek=Model(isim,fiyat,url)
                                 foodArray.add(yemek)
                                 idArray.add(id)
                             }
                         }
                     }
                 }
             }

         }

         }
    }


}