package com.hozanbaydu.getirsearch.dependencyinjections

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hozanbaydu.getirsearch.R
import com.hozanbaydu.getirsearch.firebase.GetData
import com.hozanbaydu.getirsearch.viewmodel.SearchViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {


    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context)= Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)

                .error(R.drawable.ic_launcher_foreground)
        )




}