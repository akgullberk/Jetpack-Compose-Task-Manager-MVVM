package com.example.taskmanager.uix.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.taskmanager.data.repo.GorevlerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GorevEklemeViewModel() : ViewModel() {
    var grepo = GorevlerRepository()
    fun kaydet(GorevAd : String){
        CoroutineScope(Dispatchers.Main).launch {
            grepo.kaydet(GorevAd)
        }
    }
}