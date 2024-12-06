package com.example.taskmanager.uix.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskmanager.data.entity.Gorevler
import com.example.taskmanager.data.repo.GorevlerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnasayfaViewModel : ViewModel() {
    var grepo = GorevlerRepository()
    var gorevlerListesi = MutableLiveData<List<Gorevler>>()

    init {
        gorevleriYukle()
    }

    fun gorevleriYukle() {
        CoroutineScope(Dispatchers.Main).launch {
            gorevlerListesi.value = grepo.gorevleriYukle()
        }
    }
}