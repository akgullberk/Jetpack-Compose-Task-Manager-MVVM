package com.example.taskmanager.uix.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.taskmanager.R
import com.example.taskmanager.uix.viewmodel.GorevEklemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GorevEkleme(navController: NavController, gorevEklemeViewModel: GorevEklemeViewModel){
    val tfGorevAd = remember {
        mutableStateOf("")
    }
    fun handleFloatButtonClick(navController: NavController){
        navController.navigate("anasayfa")
        gorevEklemeViewModel.kaydet(tfGorevAd.value)

    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Görev Ekleme") },
                colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF127369),
                titleContentColor = Color.White,

            ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("anasayfa") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.geri_oku), // Burada ikonunuzu belirtin
                            contentDescription = "Menu Icon",
                            tint = Color.White // İkon rengi
                        )
                    }
                },

            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { handleFloatButtonClick(navController) },
                content = {
                    Icon(painter = painterResource(id = R.drawable.check), contentDescription = "")
                }
            )
        }

    ) { paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(Color(0xFF8AA6A3))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(text = "Ne Yapılacak?")
                TextField(value = tfGorevAd.value, onValueChange = {tfGorevAd.value = it}, label = { Text(text = "Görev Ekle...")},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedLabelColor = Color.Black,
                        focusedIndicatorColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        unfocusedIndicatorColor = Color.Black,
                    ))

            }
        }



    }
}