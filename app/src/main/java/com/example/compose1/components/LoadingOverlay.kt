package com.example.compose1.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose1.ui.theme.DeepsCompose1Theme

@Composable
fun LoadingOverlay(
    modifier: Modifier = Modifier,
    showFullCircleInPreview: Boolean = false
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White.copy(alpha = 0.6f))
            .wrapContentSize(Alignment.Center)
    ) {
        if (showFullCircleInPreview && LocalInspectionMode.current) {

            CircularProgressIndicator(
                progress = { 1f },
                modifier = Modifier.size(64.dp),
                color = Color(0xFFFF9800),
                strokeWidth = 6.dp,
                trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
            )
        } else {

            CircularProgressIndicator(
                modifier = Modifier.size(64.dp),
                color = Color(0xFFFF9800),
                strokeWidth = 6.dp,
                trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
            )
        }
    }
}

@Preview(
    showBackground = true/*,
    widthDp = 891,
    heightDp = 411,
    name = "Landscape Preview" */
)
@Composable
fun LoadingOverlayPreview(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White.copy(alpha = 0.6f)
) {
    DeepsCompose1Theme {
        LoadingOverlay(showFullCircleInPreview = true)
    }
}