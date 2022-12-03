# GetirSearch
Getir yemek uygulaması için içeriğe göre yemek öneri özelliği eklenmesi.

Aşağıdaki videodan uygulamanın son halini görebilirsiniz.


https://user-images.githubusercontent.com/113553307/205436946-cdf26ade-4548-4f62-ac89-836fb0bf1fa0.mp4

## Özellikler


- Kullanıcı istediği bir içeriği yemek önerisi için kullanabilir.
- İçeriklere önem derecesi vererek öneri sisteminin daha iyi sonuçlar vermesini sağlayabilir.

Not:Yazının okunma süresi 3 dakikadır.

### Giriş sayfası

![gettir1](https://user-images.githubusercontent.com/113553307/205437589-a33c535a-e6d8-4942-86b3-ff8389136199.png)


### Arama sayfası

![gettir2](https://user-images.githubusercontent.com/113553307/205437599-7eaceda3-462d-4d0a-97f1-c34b374c6fe4.png)


Kullanıcı bu sayfadan önerilmesini istediği içerikleri ve önem derecesini belirlemelidir.

```sh
var textView=binding.nameText.text.toString()
            var textView2= binding.nameText2.text.toString()
            var textView3= binding.nameText3.text.toString()
            var textView4= binding.nameText4.text.toString()
            
            var list:MutableList<String> = mutableListOf(textView,textView2,textView3,textView4)
            viewModel.dataList.postValue(list)

            var importanceArrayList=viewModel.imortancelist
            importanceArrayList.clear()
```
Yukarıdaki kodlar kullanıcının girdiği içerikleri viewmodelde tanımladığım listeye atar.Bunun sayesinde veri tabanına istek atarken önerilecek içerikleri belirleyebiliriz.


```sh
dependencyResolutionManagement {
    importanceArrayList.add(0, binding.textView.text.toString().toInt())
            importanceArrayList.add(1, binding.textView2.text.toString().toInt())
            importanceArrayList.add(2, binding.textView3.text.toString().toInt())
            importanceArrayList.add(3, binding.textView4.text.toString().toInt())
}
```
İstek içeriklerin önem derecesini yukarıdaki kod parçası sayesinde sistem algılar.
Bu bilgileri aşağıda kodları görülen seekBar sayesinde kullanıcıdan alınır.

```sh
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
```

Alınan liste aşağıda görülen veri çekme fonksiyonu sayesinde veri tabanında eşleşen verilerin idsini alıp bir listeye ekler.

```sh
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
```

### Sonuç sayfası

![gettir3](https://user-images.githubusercontent.com/113553307/205437619-ffc1870d-34bd-4c0d-8ad2-7ced1402155a.png)


Son olarak idleri içeren liste sayesinde veri tabanından sonuç fragmentinde resultData fonksiyonu çağrılarak uygun sonuçlar gösterilir.
```sh
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
```

Kodlar uzun olduğu için hepsini burda açıklamam mümkün olmadı.Tüm kodllara proje dosyasından ulaşabilirsiniz.Okuduğunuz için teşekkür ederim.

Uygulamayı yazan:Hozan BAYDU

Tasarım:Adobexd,Canva

Tarih:20.11.2022

iletişim:hozan.baydu3447@gmail.com

