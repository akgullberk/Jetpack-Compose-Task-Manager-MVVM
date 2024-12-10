package com.example.taskmanager.uix.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.taskmanager.R

@Composable
fun FloatButton(
    onClick: () -> Unit,
    iconResId: Int,
){
    FloatingActionButton(
        onClick = onClick ,
        content = {
            Icon(painter = painterResource(id = iconResId), contentDescription = "")
        }
    )
}