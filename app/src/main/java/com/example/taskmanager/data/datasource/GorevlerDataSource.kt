package com.example.taskmanager.data.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.taskmanager.data.entity.Gorevler
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

class GorevlerDataSource(var collectionGorevler : CollectionReference) {
    var gorevlerListesi = MutableLiveData<List<Gorevler>>()
    fun kaydet(GorevAd : String,gorev_tarihi: String){
        val yeniGorev = Gorevler("",GorevAd,gorev_tarihi,timestamp = System.currentTimeMillis())
        collectionGorevler.document().set(yeniGorev)

    }

    fun sil(gorev_id:String){
        collectionGorevler.document(gorev_id).delete()
    }

     fun gorevleriYukle() : MutableLiveData<List<Gorevler>>{

        collectionGorevler
            .orderBy("timestamp", Query.Direction.DESCENDING)  // Verileri timestamp alanına göre sıralıyoruz
            .addSnapshotListener { value, error ->
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

    fun ara(aramaKelimesi:String) : MutableLiveData<List<Gorevler>> {
        collectionGorevler
            .orderBy("timestamp", Query.Direction.DESCENDING)  // Verileri timestamp alanına göre sıralıyoruz
            .addSnapshotListener { value, error ->
            if(value != null){
                val liste = ArrayList<Gorevler>()

                for(d in value.documents){
                    val gorev = d.toObject(Gorevler::class.java)
                    if(gorev != null){
                        if(gorev.gorev_adi!!.lowercase().contains(aramaKelimesi.lowercase())){
                            gorev.gorev_id = d.id
                            liste.add(gorev)
                        }
                    }
                }

                gorevlerListesi.value = liste
            }
        }
        return gorevlerListesi
    }
}