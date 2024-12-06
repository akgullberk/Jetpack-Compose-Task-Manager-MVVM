package com.example.taskmanager.data.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.taskmanager.data.entity.Gorevler
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GorevlerDataSource(var collectionGorevler : CollectionReference) {
    var gorevlerListesi = MutableLiveData<List<Gorevler>>()
    fun kaydet(GorevAd : String){
        val yeniGorev = Gorevler("",GorevAd)
        collectionGorevler.document().set(yeniGorev)
    }

     fun gorevleriYukle() : MutableLiveData<List<Gorevler>>{

        collectionGorevler.addSnapshotListener { value, error ->
            if(value!= null){
                val liste = ArrayList<Gorevler>()

                for(d in value.documents){
                    val gorev = d.toObject(Gorevler::class.java)
                    if(gorev!= null){
                        gorev.gorev_id = d.id
                        liste.add(gorev)
                    }
                }
                gorevlerListesi.value = liste
            }
        }

        return gorevlerListesi

    }
}