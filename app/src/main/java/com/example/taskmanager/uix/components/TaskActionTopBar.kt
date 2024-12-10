package com.example.taskmanager.uix.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.taskmanager.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskActionTopBar(
    title : String,
    navController: NavController,
    navigationDestination: String

){
    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF127369),
            titleContentColor = Color.White,
            ),
        navigationIcon = {
            IconButton(onClick = { navController.navigate(navigationDestination) }) {
                Icon(
                    painter = painterResource(id = R.drawable.geri_oku), // Burada ikonunuzu belirtin
                    contentDescription = "Menu Icon",
                    tint = Color.White // İkon rengi
                )
            }
        },

        )

}