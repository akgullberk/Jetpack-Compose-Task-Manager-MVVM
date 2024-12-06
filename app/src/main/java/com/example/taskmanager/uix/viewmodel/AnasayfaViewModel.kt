package com.example.taskmanager.uix.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskmanager.data.entity.Gorevler
import com.example.taskmanager.data.repo.GorevlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(var grepo:GorevlerRepository) : ViewModel() {

    var gorevlerListesi = MutableLiveData<List<Gorevler>>()

    init {
        gorevleriYukle()
    }

    fun gorevleriYukle() {
        gorevlerListesi = grepo.gorevleriYukle()
    }

    fun sil(gorev_id:String){
        grepo.sil(gorev_id)
    }

    fun ara(aramaKelimesi:String) {
        gorevlerListesi = grepo.ara(aramaKelimesi)
    }
}