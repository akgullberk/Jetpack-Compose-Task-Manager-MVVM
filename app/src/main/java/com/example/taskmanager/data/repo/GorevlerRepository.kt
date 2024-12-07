package com.example.taskmanager.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.taskmanager.data.datasource.GorevlerDataSource
import com.example.taskmanager.data.entity.Gorevler
import java.time.LocalDate

class GorevlerRepository(var gds:GorevlerDataSource) {

     fun kaydet(GorevAd : String,gorev_tarihi: String) = gds.kaydet(GorevAd,gorev_tarihi)

     fun gorevleriYukle() : MutableLiveData<List<Gorevler>> = gds.gorevleriYukle()

     fun sil(gorev_id:String) = gds.sil(gorev_id)

     fun ara(aramaKelimesi:String) : MutableLiveData<List<Gorevler>> = gds.ara(aramaKelimesi)
}