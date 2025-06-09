package com.example.compose1.extensions

import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat
import dagger.Component

fun ComponentActivity.setEdgeToEdge(enabled: Boolean) {
    WindowCompat.setDecorFitsSystemWindows(window, !enabled)
}