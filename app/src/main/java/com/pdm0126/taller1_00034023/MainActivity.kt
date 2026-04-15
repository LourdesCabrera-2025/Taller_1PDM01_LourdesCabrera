package com.pdm0126.taller1_00034023

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm0126.taller1_00034023.ui.theme.AndroidPediaByCabreraTheme
import org.intellij.lang.annotations.JdkConstants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidPediaByCabreraTheme {
                App()
            }
        }
    }
}

@Composable
fun WelcomeScreen (onStartClick: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF3DDC84),
                            Color(0xFFF6F8F7)
                        )
                    )
                )
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "AndroidPedia",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "¿Cuanto sabes de Android?",
                            fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(text = "Lourdes Isabel Cabrera Clímaco")
                        Text(text = "Carnet: 00034023")

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = { },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Comenzar Quiz")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuizSreen () {

    
}

@Composable
fun App () {
    var pantalla by remember { mutableStateOf("Inicio") }

    when(pantalla) {
        "Inicio" -> WelcomeScreen {pantalla = "quiz"}
        "quiz" -> QuizSreen()
    }

}


