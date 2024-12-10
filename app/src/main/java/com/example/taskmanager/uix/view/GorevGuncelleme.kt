package com.example.taskmanager.uix.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.taskmanager.R
import com.example.taskmanager.uix.components.FloatButton
import com.example.taskmanager.uix.components.TaskActionTopBar

@Composable
fun GorevGuncelleme(navController: NavController){
    Scaffold(
        topBar = {
            TaskActionTopBar(title = "Görev Güncelleme", navController =navController , navigationDestination = "anasayfa")
        },
        floatingActionButton = {
            FloatButton(onClick = { /*TODO*/ }, iconResId = R.drawable.check)
        }
    ){ paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize() // Sayfanın tüm alanını kaplar
                .background(Color(0xFF8AA6A3))
        ){

        }

    }


}