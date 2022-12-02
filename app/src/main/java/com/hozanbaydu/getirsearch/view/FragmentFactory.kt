package com.hozanbaydu.getirsearch.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class FragmentFactory @Inject constructor(

): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){

            else -> return super.instantiate(classLoader, className)
        }
    }
}