package me.index197511.githubrepositoryviewer.androidApp.ui.common.repository

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun Description(description: String) {
    Text(
        text = description,
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.wrapContentHeight()
    )
}