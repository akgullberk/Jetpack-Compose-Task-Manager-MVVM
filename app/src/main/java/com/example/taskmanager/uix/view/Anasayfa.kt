package com.example.taskmanager.uix.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.taskmanager.R
import com.example.taskmanager.uix.viewmodel.AnasayfaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController: NavController,anasayfaViewModel: AnasayfaViewModel){
    val gorevlerListesi =  anasayfaViewModel.gorevlerListesi.observeAsState(listOf())

    LaunchedEffect(key1 = true) {
        anasayfaViewModel.gorevleriYukle()
        //Anasayfaya geri dönüldüğünde çalışır.
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Anasayfa")})
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("gorevEkleme") },
                content = {
                    Icon(painter = painterResource(id = R.drawable.ekle_resim), contentDescription = "")
                }
            )
        },
    ) { paddingValues ->  
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(count = gorevlerListesi.value.count(),
                    itemContent = {
                        val gorev = gorevlerListesi.value[it]
                        Text(text = gorev.gorev_adi)
                    }
                    )
            }
    }
}