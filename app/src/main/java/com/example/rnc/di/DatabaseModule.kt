package com.example.rnc.di

import android.content.Context
import com.example.rnc.data.EntityRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRepo(@ApplicationContext appContext: Context)
    : EntityRepo = EntityRepo()
}