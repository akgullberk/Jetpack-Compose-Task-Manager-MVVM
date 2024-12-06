package com.example.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@Preview(showBackground = true)
@Composable
fun AnasayfaPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        Anasayfa() // Burada kendi composable'ınızı çağırabilirsiniz.
    }
}

@Composable
fun Anasayfa(){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
    )  {
        Row(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),


            ) {
            Button(onClick = {  },
                modifier = Modifier.size(20.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                shape = RectangleShape,
                border = BorderStroke(2.dp, Color.Gray)) {

            }

            Text(text = "dsadsad")
        }
    }
}




