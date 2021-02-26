package me.index197511.githubrepositoryviewer.androidApp.ui.common.repository

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun Star(star: Int) {
    Text(
        text = "Star: $star",
        fontSize = 12.sp,
        color = Color.Gray
    )
}