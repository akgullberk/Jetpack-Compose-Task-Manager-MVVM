package com.example.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskmanager.ui.theme.TaskManagerTheme
import com.example.taskmanager.uix.view.Anasayfa
import com.example.taskmanager.uix.view.SayfaGecisleri
import com.example.taskmanager.uix.viewmodel.AnasayfaViewModel
import com.example.taskmanager.uix.viewmodel.GorevEklemeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val gorevEklemeViewModel : GorevEklemeViewModel by viewModels()
    val anasayfaViewModel : AnasayfaViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskManagerTheme {
                SayfaGecisleri(gorevEklemeViewModel,anasayfaViewModel)
            }
        }
    }
}

