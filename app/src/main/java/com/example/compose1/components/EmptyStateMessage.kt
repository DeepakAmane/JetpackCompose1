package com.example.compose1.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose1.ui.theme.DeepsCompose1Theme

@Composable
fun EmptyStateMessage(
    modifier: Modifier = Modifier,
    message: String = "No response from the server."
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = "Connection Error",
                tint = Color(0xFFFF9800),
                modifier = Modifier.size(72.dp)
            )
            Text(
                text = message,
                color = Color.Gray,
                fontSize = 18.sp
            )
        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmptyStateMessagePreview() {
    DeepsCompose1Theme {
        EmptyStateMessage()
    }
}