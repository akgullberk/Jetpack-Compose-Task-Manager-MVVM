package com.example.taskmanager.data.datasource

import android.util.Log
import com.example.taskmanager.data.entity.Gorevler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GorevlerDataSource {
    suspend fun kaydet(GorevAd : String){
        Log.e("Gorev Ekleme",GorevAd)
    }

    suspend fun gorevleriYukle() : List<Gorevler> = withContext(Dispatchers.IO){
        val gorevlerListesi = ArrayList<Gorevler>()
        val g1 = Gorevler(1,"Odanı Topla")
        val g2 = Gorevler(2,"Kahvaltı Hazırla")
        val g3 = Gorevler(3,"Belgeyi Unutma")
        gorevlerListesi.add(g1)
        gorevlerListesi.add(g2)
        gorevlerListesi.add(g3)

        return@withContext gorevlerListesi

    }
}