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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm0126.taller1_00034023.ui.theme.AndroidPediaByCabreraTheme

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
fun App () {
    var pantalla by remember { mutableStateOf("Inicio") }
    var puntajeFinal by remember { mutableIntStateOf(0) }

    when(pantalla) {
        "Inicio" -> WelcomeScreen {pantalla = "quiz"}
        "quiz" -> QuizScreen(
            onFinishQuiz = {puntaje ->
                puntajeFinal = puntaje
                pantalla = "resultado"
            }
        )
        "resultado" -> ResultScreen (
            score = puntajeFinal,
            onRestart = {pantalla = "Inicio"}
        )
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
                            Color(0xFF121212),
                            Color(0xFF1E1E1E)
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
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF8A8989)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "AndroidPedia",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE0E0E0)
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "¿Cuanto sabes de Android?",
                            fontSize = 16.sp,
                            color = Color(0xFFE0E0E0)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(text = "Lourdes Isabel Cabrera Clímaco", color = Color(0xFFE0E0E0))
                        Text(text = "Carnet: 00034023",color = Color(0xFFE0E0E0))

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = {onStartClick()},
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF077238)
                            )
                        ) {
                            Text("Comenzar Quiz", color = Color(0xFFE0E0E0))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuizScreen(onFinishQuiz: (Int) -> Unit) {
    var preguntaActual by remember { mutableStateOf(0) }
    var puntaje by remember { mutableIntStateOf(0) }
    var seleccion by remember { mutableStateOf<String?>(null) }
    var respuesta by remember { mutableStateOf(false) }

    val totalPreguntas = quizQuestion.size
    val pregunta = quizQuestion[preguntaActual]
    val progreso = (preguntaActual + 1) / totalPreguntas.toFloat()

        Scaffold(modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent) {innerPadding ->
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF121212),
                            Color(0xFF1E1E1E)
                        )
                    )
                )
                .padding(innerPadding),
                contentAlignment = Alignment.Center

            ){

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "Quiz de Cultura Android" ,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE0E0E0)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF1E1E1E)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {
                            Text(
                                text = "Pregunta ${preguntaActual + 1} de $totalPreguntas",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFE0E0E0)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            LinearProgressIndicator(
                                progress = progreso,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp),
                                color=Color(0xFF3DDC84),
                                trackColor = Color.LightGray
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text="Puntaje: $puntaje / $totalPreguntas",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFFE0E0E0)
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Card (
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFF1E1E1E)
                                )
                            ) {
                                Text(
                                    text = pregunta.question,
                                    modifier = Modifier.padding(16.dp),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color(0xFFE0E0E0)
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            pregunta.options.forEach { opcion ->
                                val colorBotton = when {
                                    !respuesta -> Color(0xFF2E7D32)
                                    opcion == pregunta.correctAnswer -> Color(0xFF4CAF50)
                                    opcion == seleccion && opcion != pregunta.correctAnswer->Color(0xFFE53935)
                                    else -> Color(0xFF3F3D3D)
                                }
                                Button(
                                    onClick = {
                                        if(!respuesta) {
                                            seleccion = opcion
                                            respuesta = true

                                            if(opcion == pregunta.correctAnswer) {
                                                puntaje++
                                            }
                                        }
                                    },
                                    enabled = true,
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = colorBotton,
                                        disabledContentColor = colorBotton
                                    ),
                                    shape = RoundedCornerShape(14.dp)
                                ) {
                                    Text(opcion, color = Color(0xFFE0E0E0))
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                            if(respuesta) {
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Fun Fact: ${pregunta.funFact}",
                                    fontSize = 15.sp,
                                    color = Color(0xFFE0E0E0),
                                    fontWeight = FontWeight.Medium

                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Button(
                                    onClick = {
                                        if(preguntaActual < totalPreguntas -1) {
                                            preguntaActual ++
                                            seleccion = null
                                            respuesta = false
                                        } else {
                                            onFinishQuiz(puntaje)
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF077238)
                                    )
                                ) {
                                    Text(
                                        if(preguntaActual == totalPreguntas -1)
                                        "ver resultados"
                                        else
                                            "siguiente"
                                       ,
                                        color = Color(0xFFE0E0E0)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
}

@Composable

fun ResultScreen (score: Int, onRestart: () -> Unit) {

    val mensaje = when (score) {
        3->"¡Excelente! Eres un Android Master"
        2->"¡Muy bien ! Sabes bastante de Android"
        1->"Vas bien, pero puedes mejorar"
        else -> "Sigue intentando ¡Tu puedes!"
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent
    ) {innerPadding ->
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
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1E1E1E)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Resultado Final",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE0E0E0)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Obtuviste $score de 3",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFE0E0E0)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = mensaje,
                            fontSize = 16.sp,
                            color = Color(0xFFE0E0E0)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = onRestart,
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF077238)
                            )
                        ) {
                            Text("Reiniciar Quiz")
                        }
                    }
                }
            }
        }
    }
}






