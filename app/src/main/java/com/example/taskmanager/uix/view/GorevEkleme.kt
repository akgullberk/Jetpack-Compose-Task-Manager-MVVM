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

    val textState = remember { mutableStateOf("") }

    // Text'in stilini belirleme
    val textStyle = TextStyle(fontSize = 16.sp)

    // TextHeight'i dinamik olarak güncellemek için
    val textHeight = remember { mutableStateOf(0.dp) }

    // LocalDensity ile piksel ölçümlerine dönüştürme işlemi
    val density = LocalDensity.current

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

    fun Int.toDp(): Dp {
        return (this / Resources.getSystem().displayMetrics.density).dp
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
            .verticalScroll(rememberScrollState()) // Kaydırma işlemi burada sağlanıyor
            .background(Color(0xFF8AA6A3))
        ) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            ) {
                Text(
                    text = "Ne Yapılacak?", color = Color(0xFF127369), fontWeight = FontWeight.Bold, fontSize = 18.sp)



                BasicTextField(
                    value = tfGorevAd.value,
                    onValueChange = {tfGorevAd.value = it},
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 32.dp)
                                .background(Color.Transparent)
                                .drawBehind {
                                    val strokeWidth = 2.dp.toPx()
                                    val y = size.height - strokeWidth / 2
                                    drawLine(
                                        color = Color(0xFF127369),
                                        start = Offset(0f, y),
                                        end = Offset(size.width, y),
                                        strokeWidth = strokeWidth
                                    )
                                }
                                .onGloballyPositioned { coordinates ->
                                    // Metnin yüksekliğini hesaplamak için
                                    val height = with(density) {
                                        // Text'in satır sayısını alıp, yükseklik hesaplaması yapıyoruz
                                        textStyle.fontSize.toPx() * textState.value.lines().size
                                    }
                                    textHeight.value = height.dp
                                },

                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = Color(0xFFEEEEEE)
                    ),
                    singleLine = false, // Çok satırlı olmasını sağlıyoruz,
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier.fillMaxWidth()
                                .padding(bottom = 4.dp),
                            contentAlignment = Alignment.BottomStart // Varsayılan hizalama
                        ) {

                            if (tfGorevAd.value.isEmpty()) {
                                // Boşsa "Hint" göster
                                Text(
                                    text = "Buraya görevi girin",
                                    color = Color(0xFF616161),
                                    fontSize = 16.sp,
                                )
                            }

                            innerTextField() // Gerçek metin alanı
                        }
                    },

                )
                
                Spacer(modifier = Modifier.padding(30.dp))

                Text(
                    text = "Sona Erme Tarihi", color = Color(0xFF127369), fontWeight = FontWeight.Bold, fontSize = 18.sp)


                    Row {
                        BasicTextField(
                            value = cdGorevTarihi.value,
                            onValueChange = {cdGorevTarihi.value = it},
                            modifier = Modifier
                                .weight(1f)
                                .heightIn(min = 32.dp)
                                .background(Color.Transparent)
                                .drawBehind {
                                    val strokeWidth = 2.dp.toPx()
                                    val y = size.height - strokeWidth / 2
                                    drawLine(
                                        color = Color(0xFF127369),
                                        start = Offset(0f, y),
                                        end = Offset(size.width, y),
                                        strokeWidth = strokeWidth
                                    )
                                }
                            ,
                            readOnly = true,
                            textStyle = TextStyle(
                                fontSize = 20.sp,
                                color = Color(0xFFEEEEEE)


                            ),

                            decorationBox = { innerTextField ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 4.dp)
                                        .clickable { calendarState.show() },
                                    contentAlignment = Alignment.BottomStart // Varsayılan hizalama
                                ) {

                                    if (cdGorevTarihi.value.isEmpty()) {
                                        // Boşsa "Hint" göster
                                        Text(
                                            text = "Tarih yok",
                                            color = Color(0xFF616161),
                                            fontSize = 16.sp,
                                        )
                                    }

                                    innerTextField() // Gerçek metin alanı
                                }
                            },
                        )

                        Spacer(modifier = Modifier.width(8.dp)) // TextField ve Icon arasında boşluk

                        Icon(
                            painter = painterResource(id = R.drawable.calendar),
                            contentDescription = "Onay ikonu",
                            tint = Color(0xFF127369), // İkonun rengi
                            modifier = Modifier
                                .size(32.dp) // İkonun boyutu
                                .clickable { calendarState.show() }
                        )
                    }




            }
        }
    }
}

