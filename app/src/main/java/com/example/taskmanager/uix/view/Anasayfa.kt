package com.example.taskmanager.uix.view

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskmanager.R
import com.example.taskmanager.uix.viewmodel.AnasayfaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController: NavController,anasayfaViewModel: AnasayfaViewModel){
    val gorevlerListesi =  anasayfaViewModel.gorevlerListesi.observeAsState(listOf())
    val gorevDurumlari = remember { mutableStateMapOf<Int, Boolean>() }

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
                items(count = gorevlerListesi.value.count()) { index ->
                    val gorev = gorevlerListesi.value[index]
                    val isStriked = gorevDurumlari[index] ?: false // Varsayılan olarak çizili değil

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly



                        ) {
                            val buttonColor = if (isStriked) Color(0xFF8AA6A3) else Color.Transparent

                            Button(
                                onClick = {
                                    // Çizili durumunu değiştir
                                    gorevDurumlari[index] = !(gorevDurumlari[index] ?: false)
                                },
                                modifier = Modifier.size(20.dp),
                                colors = ButtonDefaults.buttonColors(buttonColor),
                                shape = RectangleShape,
                                border = BorderStroke(2.dp, if (isStriked) Color(0xFF8AA6A3) else Color.Gray )
                            ) {

                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = gorev.gorev_adi ?: "",
                                modifier = Modifier.weight(1f),
                                textDecoration = if (isStriked) TextDecoration.LineThrough else TextDecoration.None // Çizili mi kontrolü
                            )



                            Icon(painter = painterResource(id = R.drawable.kapat_resim),
                                contentDescription ="",
                                modifier = Modifier
                                    .clickable { anasayfaViewModel.sil(gorev.gorev_id!!)
                                    }
                                    .size(25.dp)
                            )

                        }
                    }
                }
            }
        }

    }
}