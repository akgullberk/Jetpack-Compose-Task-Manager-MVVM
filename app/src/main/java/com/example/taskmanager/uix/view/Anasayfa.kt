package com.example.taskmanager.uix.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
            TopAppBar(
                title = { Text(text = "Anasayfa")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF127369),
                    titleContentColor = Color.White
                    ))
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
        Box(
            modifier = Modifier
                .fillMaxSize() // Sayfanın tüm alanını kaplar
                .background(Color(0xFF8AA6A3))
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(count = gorevlerListesi.value.count(),
                    itemContent = {
                        val gorev = gorevlerListesi.value[it]
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        )  {
                            Row(modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),

                                verticalAlignment = Alignment.CenterVertically



                            ) {
                                Button(onClick = {  },
                                    modifier = Modifier.size(20.dp),
                                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                                    shape = RectangleShape,
                                    border = BorderStroke(2.dp, Color.Gray)
                                ) {

                                }
                                Spacer(modifier = Modifier.width(8.dp))

                                Text(text = gorev.gorev_adi!!)

                            }
                        }

                    }
                )
            }
        }

    }
}