package com.example.taskmanager.uix.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GorevTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint : String
){

    // LocalDensity ile piksel ölçümlerine dönüştürme işlemi
    val density = LocalDensity.current

    // Text'in stilini belirleme
    val textStyle = TextStyle(fontSize = 16.sp)

    val textState = remember { mutableStateOf("") }

    // TextHeight'i dinamik olarak güncellemek için
    val textHeight = remember { mutableStateOf(0.dp) }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                contentAlignment = Alignment.BottomStart // Varsayılan hizalama
            ) {

                if (value.isEmpty()) {
                    // Boşsa "Hint" göster
                    Text(
                        text = hint,
                        color = Color(0xFF616161),
                        fontSize = 16.sp,
                    )
                }

                innerTextField() // Gerçek metin alanı
            }
        },

        )

}
