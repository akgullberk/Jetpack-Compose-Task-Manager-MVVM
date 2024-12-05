package com.example.taskmanager.data.repo

import com.example.taskmanager.data.datasource.GorevlerDataSource

class GorevlerRepository {
    var gds = GorevlerDataSource()

    suspend fun kaydet(GorevAd : String) = gds.kaydet(GorevAd)
}