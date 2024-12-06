package com.example.taskmanager.di

import com.example.taskmanager.data.datasource.GorevlerDataSource
import com.example.taskmanager.data.repo.GorevlerRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideGorevlerRepository(gds:GorevlerDataSource) : GorevlerRepository{
        return GorevlerRepository(gds)
    }

    @Provides
    @Singleton
    fun provideGorevlerDataSource(collectionGorevler : CollectionReference) : GorevlerDataSource{
        return GorevlerDataSource(collectionGorevler)
    }

    @Provides
    @Singleton
    fun provideCollectionReference() : CollectionReference{
        return Firebase.firestore.collection("Gorevler")
    }
}