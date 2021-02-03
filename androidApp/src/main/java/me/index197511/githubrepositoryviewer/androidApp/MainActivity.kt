package me.index197511.githubrepositoryviewer.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.index197511.githubrepositoryviewer.shared.Greeting
import android.widget.TextView
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val tv: TextView = findViewById(R.id.text_view)
//        tv.text = greet()
        setContent {
            Hello()
        }
    }
}

@Composable
fun Hello() {
    Text(text = "Hello")
}

@Preview
@Composable
fun HelloPreview() {
    Hello()
}
