package com.cortez.examen2parcial.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cortez.examen2parcial.data.ExamenData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamenScreen(
    navController: NavController,
    onRespuestasComplete: (List<Int>) -> Unit
) {
    val preguntas = ExamenData.preguntas
    var preguntaActual by remember { mutableStateOf(0) }

    // Uso mutableStateMapOf para manejar las respuestas de forma más eficiente
    val respuestasSeleccionadas = remember {
        mutableStateMapOf<Int, Int>().apply {
            for (i in 0 until preguntas.size) {
                this[i] = -1 // -1 significa que no ha seleccionado respuesta
            }
        }
    }

    // Obtengo la respuesta actual para facilitar el acceso
    val respuestaActual = respuestasSeleccionadas[preguntaActual] ?: -1

    // Diseño formal con colores corporativos
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA)) // Fondo gris claro profesional
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            // Header corporativo con información del progreso
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Icono formal
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Examen",
                        modifier = Modifier.size(32.dp),
                        tint = Color(0xFF1F2937)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "EVALUACIÓN",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1F2937),
                        letterSpacing = 0.5.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Muestro el progreso actual
                    Text(
                        text = "Pregunta ${preguntaActual + 1} de ${preguntas.size}",
                        fontSize = 14.sp,
                        color = Color(0xFF6B7280),
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Barra de progreso sobria y profesional
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Progreso",
                                fontSize = 12.sp,
                                color = Color(0xFF6B7280)
                            )
                            Text(
                                text = "${((preguntaActual + 1) * 100 / preguntas.size)}%",
                                fontSize = 12.sp,
                                color = Color(0xFF6B7280),
                                fontWeight = FontWeight.Medium
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(Color(0xFFE5E7EB))
                        ) {
                            // Barra de progreso con color corporativo
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth((preguntaActual + 1).toFloat() / preguntas.size)
                                    .fillMaxHeight()
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(Color(0xFF374151))
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Card principal para la pregunta actual
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(28.dp)
                ) {
                    // Cabecera de la pregunta
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Pregunta ${preguntaActual + 1}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF6B7280),
                            modifier = Modifier
                                .background(
                                    Color(0xFFF3F4F6),
                                    RoundedCornerShape(4.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )

                        // Indicador si ya respondió esta pregunta
                        if (respuestaActual != -1) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Respondida",
                                    tint = Color(0xFF059669),
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Respondida",
                                    fontSize = 12.sp,
                                    color = Color(0xFF059669),
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Texto de la pregunta con tipografía formal
                    Text(
                        text = preguntas[preguntaActual].pregunta,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1F2937),
                        lineHeight = 24.sp,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    // Opciones de respuesta con diseño corporativo
                    preguntas[preguntaActual].opciones.forEachIndexed { index, opcion ->
                        val isSelected = respuestaActual == index

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable {
                                    // Actualizo la respuesta seleccionada
                                    respuestasSeleccionadas[preguntaActual] = index
                                },
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = if (isSelected) 1.dp else 0.dp
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isSelected)
                                    Color(0xFF1F2937)
                                else
                                    Color(0xFFFAFAFA)
                            ),
                            shape = RoundedCornerShape(6.dp),
                            border = BorderStroke(
                                width = 1.dp,
                                color = if (isSelected)
                                    Color(0xFF374151)
                                else
                                    Color(0xFFE5E7EB)
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Indicador visual tipo radio button formal
                                Box(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(
                                            if (isSelected)
                                                Color.White
                                            else
                                                Color(0xFFE5E7EB)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (isSelected) {
                                        Text(
                                            text = "✓",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF1F2937)
                                        )
                                    } else {
                                        // Letra de la opción cuando no está seleccionada
                                        Text(
                                            text = ('A' + index).toString(),
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF6B7280)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                // Texto de la opción
                                Text(
                                    text = opcion,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = if (isSelected) Color.White else Color(0xFF374151),
                                    modifier = Modifier.weight(1f),
                                    lineHeight = 20.sp
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Navegación inferior con botones corporativos
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Botón Anterior - solo visible si no es la primera pregunta
                if (preguntaActual > 0) {
                    OutlinedButton(
                        onClick = {
                            preguntaActual--
                        },
                        border = BorderStroke(1.dp, Color(0xFF6B7280)),
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier
                            .height(44.dp)
                            .width(120.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Anterior",
                            modifier = Modifier.size(16.dp),
                            tint = Color(0xFF6B7280)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Anterior",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF6B7280)
                        )
                    }
                } else {
                    // Spacer invisible para mantener el layout
                    Spacer(modifier = Modifier.width(120.dp))
                }

                // Botón Siguiente/Terminar
                if (preguntaActual < preguntas.size - 1) {
                    Button(
                        onClick = {
                            preguntaActual++
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1F2937),
                            contentColor = Color.White,
                            disabledContainerColor = Color(0xFFD1D5DB),
                            disabledContentColor = Color(0xFF9CA3AF)
                        ),
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier
                            .height(44.dp)
                            .width(120.dp)
                    ) {
                        Text(
                            text = "Siguiente",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Siguiente",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                } else {
                    // Botón terminar examen
                    Button(
                        onClick = {
                            // Convierto el mapa a lista y navego a resultados
                            val respuestasFinal = mutableListOf<Int>()
                            for (i in 0 until preguntas.size) {
                                respuestasFinal.add(respuestasSeleccionadas[i] ?: -1)
                            }
                            onRespuestasComplete(respuestasFinal)
                            navController.navigate("resultados")
                        },
                        enabled = respuestasSeleccionadas.values.all { it != -1 }, // Solo si respondió todas
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF059669),
                            contentColor = Color.White,
                            disabledContainerColor = Color(0xFFD1D5DB),
                            disabledContentColor = Color(0xFF9CA3AF)
                        ),
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier
                            .height(44.dp)
                            .width(120.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Terminar",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Finalizar",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}