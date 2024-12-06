package com.example.taskmanager.uix.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.taskmanager.data.repo.GorevlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GorevEklemeViewModel @Inject constructor(var grepo:GorevlerRepository) : ViewModel() {

    fun kaydet(GorevAd : String){
        grepo.kaydet(GorevAd)
    }
}