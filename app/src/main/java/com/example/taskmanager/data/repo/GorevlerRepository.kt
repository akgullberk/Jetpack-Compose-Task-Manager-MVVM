package com.example.taskmanager.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.taskmanager.data.datasource.GorevlerDataSource
import com.example.taskmanager.data.entity.Gorevler

class GorevlerRepository(var gds:GorevlerDataSource) {

     fun kaydet(GorevAd : String) = gds.kaydet(GorevAd)

     fun gorevleriYukle() : MutableLiveData<List<Gorevler>> = gds.gorevleriYukle()
}