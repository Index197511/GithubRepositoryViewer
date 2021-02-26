package me.index197511.githubrepositoryviewer.androidApp.ui.common.repository

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun RepositoryName(name: String) {
    Text(
        text = name,
        fontSize = 24.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold
    )
}
