package com.example.compose1.screens.employees

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose1.model.employee.Employee


@Composable
fun EmployeeCard(
    employee: Employee,
    imageUrl: String,
    name: String,
    title: String,
    modifier: Modifier = Modifier,
    onClick: (Employee) -> Unit
) {
    Card(
        modifier = modifier
            .clickable { onClick(employee) },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .weight(0.2f),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Color.Gray),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(
                modifier = Modifier
                    .width(16.dp)
                    .background(Color.Yellow)
            )


            Column(
                modifier = Modifier.weight(0.8f),
                verticalArrangement = Arrangement.Center
            ) {

                val isDark = isSystemInDarkTheme()
                val textColor = if (isDark) Color.White else Color.Black

                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineLarge.copy(lineHeight = 18.sp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFFFF9800)
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineLarge.copy(lineHeight = 18.sp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Thin,
                    color = textColor
                )
            }
        }
    }
}



