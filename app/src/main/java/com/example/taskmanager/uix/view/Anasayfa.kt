package com.example.taskmanager.uix.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.taskmanager.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController: NavController){
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
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                Text(text = "Anasayfa")

            }
    }
}