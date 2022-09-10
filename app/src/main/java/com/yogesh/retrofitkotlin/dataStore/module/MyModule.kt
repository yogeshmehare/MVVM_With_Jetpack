package com.yogesh.retrofitkotlin.dataStore.module

import android.content.Context
import com.yogesh.retrofitkotlin.dataStore.model.MyDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyModule {

    @Provides
    @Singleton
    fun provideMyDataStore(@ApplicationContext context: Context) = MyDataStore(context)
}