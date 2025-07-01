package com.cortez.examen2parcial.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cortez.examen2parcial.data.ExamenData
import com.cortez.examen2parcial.data.UserInfo
import com.cortez.examen2parcial.utlis.ZodiacalCalculator

@Composable
fun ResultadosScreen(
    userInfo: UserInfo,
    respuestasUsuario: List<Int>,
    navController: NavController
) {
    // Calculo la calificación comparando respuestas del usuario vs respuestas correctas
    val respuestasCorrectas = ExamenData.preguntas.map { it.respuestaCorrecta }
    val calificacion = respuestasUsuario.zip(respuestasCorrectas).count { it.first == it.second }

    // Uso mis funciones utilitarias para calcular edad y signo zodiacal
    val edad = ZodiacalCalculator.calcularEdad(userInfo.anio.toIntOrNull() ?: 2000)
    val signoZodiacal = ZodiacalCalculator.calcularSignoZodiacalChino(userInfo.anio.toIntOrNull() ?: 2000)
    val imagenSigno = ZodiacalCalculator.getImagenSigno(signoZodiacal)
    val porcentaje = (calificacion * 100) / ExamenData.preguntas.size

    // Determino el estado según la calificación para feedback profesional
    val estadoEvaluacion = when {
        calificacion >= 5 -> "Excelente"
        calificacion >= 3 -> "Satisfactorio"
        else -> "Necesita Mejorar"
    }

    // Diseño formal con fondo corporativo
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA)) // Fondo gris claro profesional
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Header corporativo
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Resultados",
                        modifier = Modifier.size(40.dp),
                        tint = Color(0xFF1F2937)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "EVALUACIÓN COMPLETADA",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1F2937),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.5.sp
                    )

                    Text(
                        text = "Reporte de resultados",
                        fontSize = 14.sp,
                        color = Color(0xFF6B7280),
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Información personal con diseño formal
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Participante",
                            modifier = Modifier.size(20.dp),
                            tint = Color(0xFF6B7280)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Información del Participante",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF374151)
                        )
                    }

                    Text(
                        text = "${userInfo.nombre} ${userInfo.apellidoPaterno} ${userInfo.apellidoMaterno}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1F2937),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // Información adicional organizada en tabla formal
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Columna izquierda
                        Column {
                            Text(
                                text = "Edad:",
                                fontSize = 12.sp,
                                color = Color(0xFF6B7280),
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "$edad años",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF374151)
                            )
                        }

                        // Columna central
                        Column {
                            Text(
                                text = "Sexo:",
                                fontSize = 12.sp,
                                color = Color(0xFF6B7280),
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = userInfo.sexo,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF374151)
                            )
                        }

                            // Columna derecha
                            Column {
                                Text(
                                    text = "Signo Zodiacal:",
                                    fontSize = 12.sp,
                                    color = Color(0xFF6B7280),
                                    fontWeight = FontWeight.Medium
                                )
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = imagenSigno,
                                        fontSize = 16.sp
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = signoZodiacal,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color(0xFF374151)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Resultados de la evaluación con diseño corporativo
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "RESULTADOS DE LA EVALUACIÓN",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF374151),
                            letterSpacing = 1.sp,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        // Calificación principal con diseño formal
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            Text(
                                text = "$calificacion",
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1F2937)
                            )

                            Text(
                                text = " / ${ExamenData.preguntas.size}",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF6B7280),
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }

                        Text(
                            text = "Respuestas Correctas",
                            fontSize = 14.sp,
                            color = Color(0xFF6B7280),
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        // Detalles adicionales en formato corporativo
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFF9FAFB)
                            ),
                            shape = RoundedCornerShape(6.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Porcentaje:",
                                        fontSize = 14.sp,
                                        color = Color(0xFF6B7280),
                                        fontWeight = FontWeight.Medium
                                    )
                                    Text(
                                        text = "$porcentaje%",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF374151)
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Estado:",
                                        fontSize = 14.sp,
                                        color = Color(0xFF6B7280),
                                        fontWeight = FontWeight.Medium
                                    )
                                    Text(
                                        text = estadoEvaluacion,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = when {
                                            calificacion >= 5 -> Color(0xFF059669) // Verde corporativo
                                            calificacion >= 3 -> Color(0xFFD97706) // Naranja corporativo
                                            else -> Color(0xFFDC2626)               // Rojo corporativo
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Botón para reiniciar con diseño corporativo
                Button(
                    onClick = {
                        // Navego al inicio y limpio el stack de navegación
                        navController.navigate("formulario") {
                            popUpTo("formulario") { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1F2937),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Inicio",
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Nueva Evaluación",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
