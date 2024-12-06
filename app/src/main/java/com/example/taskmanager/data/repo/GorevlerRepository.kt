package com.example.taskmanager.data.repo

import com.example.taskmanager.data.datasource.GorevlerDataSource
import com.example.taskmanager.data.entity.Gorevler

class GorevlerRepository {
    var gds = GorevlerDataSource()

    suspend fun kaydet(GorevAd : String) = gds.kaydet(GorevAd)

    suspend fun gorevleriYukle() : List<Gorevler> = gds.gorevleriYukle()
}