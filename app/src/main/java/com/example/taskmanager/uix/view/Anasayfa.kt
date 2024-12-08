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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController: NavController,anasayfaViewModel: AnasayfaViewModel){
    val gorevlerListesi =  anasayfaViewModel.gorevlerListesi.observeAsState(listOf())
    val gorevDurumlari = remember { mutableStateMapOf<Int, Boolean>() }
    val aramaYapiliyorMu = remember { mutableStateOf(false) }
    val tf = remember { mutableStateOf("") }


    fun handlefon(){
        anasayfaViewModel.gorevleriYukle()
        aramaYapiliyorMu.value = false
        tf.value = ""
    }



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (aramaYapiliyorMu.value){
                        TextField(
                            value = tf.value,
                            onValueChange = {
                                tf.value = it
                                anasayfaViewModel.ara(it)
                            },
                            label = { Text(text = "Ara")},
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                focusedLabelColor = Color.White,
                                focusedIndicatorColor = Color.White,
                                unfocusedLabelColor = Color.White,
                                unfocusedIndicatorColor = Color.White,
                            )
                        )
                    }else{
                        Text(text = "Görevler")
                    } },
                actions = {
                    if (aramaYapiliyorMu.value){
                        IconButton(onClick = {
                            aramaYapiliyorMu.value = false
                            tf.value = ""
                        }) { Icon(painter = painterResource(id = R.drawable.kapat_resim), contentDescription = "",tint = Color.White, modifier = Modifier.clickable { handlefon()  }) }
                    }else{
                        IconButton(onClick = {
                            aramaYapiliyorMu.value = true
                            anasayfaViewModel.gorevleriYukle()
                        }) { Icon(painter = painterResource(id = R.drawable.ara_resim), contentDescription = "",tint = Color.White) }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF127369),
                    titleContentColor = Color.White
                )
                )
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
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                items(count = gorevlerListesi.value.count()) { index ->
                    val gorev = gorevlerListesi.value[index]
                    val isStriked = gorevDurumlari[index] ?: false // Varsayılan olarak çizili değil

                    // Tarih kontrolü
                    val currentDate = Calendar.getInstance().time
                    val dateFormat = SimpleDateFormat("EEE, dd MMMM yyyy", Locale("tr", "TR"))
                    val gorevTarihi = try {
                        dateFormat.parse(gorev.gorev_tarihi ?: "")
                    } catch (e: Exception) {
                        Log.e("DateParseError", "Tarih parse edilemedi: ${gorev.gorev_tarihi}")
                        null
                    }

                    val isPast = gorevTarihi?.before(currentDate) ?: false
                    Log.d("TarihKontrol", "Görev: ${gorev.gorev_adi}, Tarih: ${gorev.gorev_tarihi}, Geçmiş mi: $isPast")


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
                            val buttonColor = if (isStriked) Color(0xFF8AA6A3)  else Color.Transparent

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

                            Column(modifier = Modifier.weight(1f),) {
                                Text(
                                    text = gorev.gorev_adi ?: "",
                                    textDecoration = if (isStriked) TextDecoration.LineThrough else TextDecoration.None, // Çizili mi kontrolü
                                    color = Color.Black
                                )

                                Text(text = gorev.gorev_tarihi ?: "",
                                    fontSize = 12.sp,
                                    color = if (isPast) Color.Red else if (gorev.gorev_tarihi == "Dün") Color.Red else Color(0xFF127369) // Tarihe göre renk
                                )
                            }




                            Icon(painter = painterResource(id = R.drawable.kapat_resim),
                                contentDescription ="",
                                modifier = Modifier
                                    .clickable {
                                        anasayfaViewModel.sil(gorev.gorev_id!!)
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

