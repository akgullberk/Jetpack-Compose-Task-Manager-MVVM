package com.example.taskmanager.uix.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmanager.uix.viewmodel.AnasayfaViewModel
import com.example.taskmanager.uix.viewmodel.GorevEklemeViewModel

@Composable
fun SayfaGecisleri(gorevEklemeViewModel: GorevEklemeViewModel,anasayfaViewModel: AnasayfaViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anasayfa"){
        composable("anasayfa"){
            Anasayfa(navController,anasayfaViewModel)
        }

        composable("gorevEkleme"){
            GorevEkleme(gorevEklemeViewModel)
        }

    }
}