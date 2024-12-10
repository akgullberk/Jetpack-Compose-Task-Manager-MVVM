package com.example.taskmanager.uix.view

import android.content.res.Resources
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskmanager.R
import com.example.taskmanager.uix.components.FloatButton
import com.example.taskmanager.uix.components.GorevTextField
import com.example.taskmanager.uix.components.TarihTextField
import com.example.taskmanager.uix.components.TaskActionTopBar
import com.example.taskmanager.uix.viewmodel.GorevEklemeViewModel
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GorevEkleme(navController: NavController, gorevEklemeViewModel: GorevEklemeViewModel){

    val tfGorevAd = remember {
        mutableStateOf("")
    }

    val cdGorevTarihi = remember{
        mutableStateOf("")
    }

    val calendarState = rememberSheetState()

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true
        ),
        selection = CalendarSelection.Date { date ->
            val today = LocalDate.now() // Bugünün tarihi
            val formattedDate = when (date) {
                today.minusDays(1) -> "Dün" // Dün
                today -> "Bugün" // Bugün
                today.plusDays(1) -> "Yarın" // Yarın
                else -> {
                    // Tarihi istediğiniz formatta yazdırın
                    val formatter = DateTimeFormatter.ofPattern("E, dd MMMM yyyy", Locale("tr", "TR"))
                    date.format(formatter)
                }
            }
            cdGorevTarihi.value = formattedDate
        }
    )
    fun handleFloatButtonClick(navController: NavController){
        navController.navigate("anasayfa")
        gorevEklemeViewModel.kaydet(tfGorevAd.value,cdGorevTarihi.value)

    }

    Scaffold(
        topBar = {
            TaskActionTopBar(
                title = "Görev Ekleme",
                navController = navController,
                navigationDestination = "anasayfa"
            )
        },
        floatingActionButton = {
            FloatButton(onClick = {handleFloatButtonClick(navController)}, iconResId = R.drawable.check)
        }

    ) { paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Kaydırma işlemi burada sağlanıyor
            .background(Color(0xFF8AA6A3))
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            ) {
                Text(
                    text = "Ne Yapılacak?",
                    color = Color(0xFF127369),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                GorevTextField(value = tfGorevAd.value, onValueChange = {tfGorevAd.value = it}, hint = "Buraya görevi girin")

                Spacer(modifier = Modifier.padding(30.dp))

                Text(
                    text = "Sona Erme Tarihi", color = Color(0xFF127369), fontWeight = FontWeight.Bold, fontSize = 18.sp)

                TarihTextField(value = cdGorevTarihi.value, onValueChange = {cdGorevTarihi.value = it}, hint ="Tarih yok", onClick = {calendarState.show()})
            }
        }
    }
}

