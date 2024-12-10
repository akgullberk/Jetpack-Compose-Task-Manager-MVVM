package com.example.taskmanager.uix.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanager.R
import com.maxkeppeker.sheets.core.models.base.rememberSheetState

@Composable
fun TarihTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint : String,
    onClick: () -> Unit
){

    Row {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .weight(1f,)
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
                        .clickable {onClick()},
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

        Spacer(modifier = Modifier.width(8.dp)) // TextField ve Icon arasında boşluk

        Icon(
            painter = painterResource(id = R.drawable.calendar),
            contentDescription = "Onay ikonu",
            tint = Color(0xFF127369), // İkonun rengi
            modifier = Modifier
                .size(32.dp) // İkonun boyutu
                .clickable { onClick() }
        )

    }

}