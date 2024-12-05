package com.example.taskmanager.data.datasource

import android.util.Log

class GorevlerDataSource {
    suspend fun kaydet(GorevAd : String){
        Log.e("Gorev Ekleme",GorevAd)
    }
}