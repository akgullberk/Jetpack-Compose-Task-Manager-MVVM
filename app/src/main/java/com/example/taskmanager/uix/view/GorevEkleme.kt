package com.example.taskmanager.uix.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.taskmanager.uix.viewmodel.GorevEklemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GorevEkleme(gorevEklemeViewModel: GorevEklemeViewModel){
    val tfGorevAd = remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Görev Ekleme") })
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier.
                padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            TextField(value = tfGorevAd.value, onValueChange = {tfGorevAd.value = it})
            Button(onClick = { gorevEklemeViewModel.kaydet(tfGorevAd.value) })
            {
                Text(text = "Kaydet")
            }
        }


    }
}