package com.example.taskmanager.uix.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskmanager.data.entity.Gorevler
import com.example.taskmanager.uix.viewmodel.AnasayfaViewModel
import com.example.taskmanager.uix.viewmodel.GorevEklemeViewModel
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SayfaGecisleri(gorevEklemeViewModel: GorevEklemeViewModel,anasayfaViewModel: AnasayfaViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anasayfa"){
        composable("anasayfa"){
            Anasayfa(navController,anasayfaViewModel)
        }

        composable("gorevEkleme"){
            GorevEkleme(navController,gorevEklemeViewModel)
        }

        composable("gorevGuncelleme"){
            GorevGuncelleme(navController)
        }

    }
}