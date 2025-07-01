// screens/FormularioScreen.kt
package com.cortez.examen2parcial.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cortez.examen2parcial.data.UserInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioScreen(
    navController: NavController,
    userInfo: UserInfo,
    onUserInfoChange: (UserInfo) -> Unit
) {
    // Manejo el estado local de todos los campos del formulario
    var nombre by remember { mutableStateOf(userInfo.nombre) }
    var apellidoPaterno by remember { mutableStateOf(userInfo.apellidoPaterno) }
    var apellidoMaterno by remember { mutableStateOf(userInfo.apellidoMaterno) }
    var dia by remember { mutableStateOf(userInfo.dia) }
    var mes by remember { mutableStateOf(userInfo.mes) }
    var anio by remember { mutableStateOf(userInfo.anio) }
    var sexo by remember { mutableStateOf(userInfo.sexo) }

    // Valido que todos los campos estén llenos antes de permitir continuar
    val todosLosCamposLlenos = nombre.isNotEmpty() &&
            apellidoPaterno.isNotEmpty() &&
            apellidoMaterno.isNotEmpty() &&
            dia.isNotEmpty() &&
            mes.isNotEmpty() &&
            anio.isNotEmpty()

    // Diseño con colores formales y profesionales
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA)) // Gris claro profesional
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Header corporativo y formal
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Icono corporativo
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Formulario",
                        modifier = Modifier.size(40.dp),
                        tint = Color(0xFF1F2937)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "FORMULARIO DE DATOS PERSONALES",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1F2937),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.5.sp
                    )

                    Text(
                        text = "Complete la información solicitada",
                        fontSize = 14.sp,
                        color = Color(0xFF6B7280),
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Formulario principal con diseño corporativo
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(32.dp)
                ) {
                    Text(
                        text = "Información Personal",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF374151),
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    // Campos de texto con diseño formal
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre *", color = Color(0xFF6B7280)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        shape = RoundedCornerShape(6.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF374151),
                            focusedLabelColor = Color(0xFF374151),
                            unfocusedBorderColor = Color(0xFFD1D5DB)
                        )
                    )

                    OutlinedTextField(
                        value = apellidoPaterno,
                        onValueChange = { apellidoPaterno = it },
                        label = { Text("Apellido Paterno *", color = Color(0xFF6B7280)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        shape = RoundedCornerShape(6.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF374151),
                            focusedLabelColor = Color(0xFF374151),
                            unfocusedBorderColor = Color(0xFFD1D5DB)
                        )
                    )

                    OutlinedTextField(
                        value = apellidoMaterno,
                        onValueChange = { apellidoMaterno = it },
                        label = { Text("Apellido Materno *", color = Color(0xFF6B7280)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        shape = RoundedCornerShape(6.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF374151),
                            focusedLabelColor = Color(0xFF374151),
                            unfocusedBorderColor = Color(0xFFD1D5DB)
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Sección para fecha de nacimiento
                    Text(
                        text = "Fecha de Nacimiento",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF374151),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // Organizo los campos de fecha en una fila
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Campo día con validación numérica
                        OutlinedTextField(
                            value = dia,
                            onValueChange = { if (it.length <= 2 && it.all { char -> char.isDigit() }) dia = it },
                            label = { Text("Día", color = Color(0xFF6B7280)) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(6.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF374151),
                                focusedLabelColor = Color(0xFF374151),
                                unfocusedBorderColor = Color(0xFFD1D5DB)
                            )
                        )

                        // Campo mes con validación numérica
                        OutlinedTextField(
                            value = mes,
                            onValueChange = { if (it.length <= 2 && it.all { char -> char.isDigit() }) mes = it },
                            label = { Text("Mes", color = Color(0xFF6B7280)) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(6.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF374151),
                                focusedLabelColor = Color(0xFF374151),
                                unfocusedBorderColor = Color(0xFFD1D5DB)
                            )
                        )

                        // Campo año con validación numérica
                        OutlinedTextField(
                            value = anio,
                            onValueChange = { if (it.length <= 4 && it.all { char -> char.isDigit() }) anio = it },
                            label = { Text("Año", color = Color(0xFF6B7280)) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(6.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF374151),
                                focusedLabelColor = Color(0xFF374151),
                                unfocusedBorderColor = Color(0xFFD1D5DB)
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Selección de sexo con diseño formal
                    Text(
                        text = "Sexo",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF374151),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // Radio buttons con diseño corporativo
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Opción Masculino
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .selectable(
                                    selected = sexo == "Masculino",
                                    onClick = { sexo = "Masculino" }
                                ),
                            colors = CardDefaults.cardColors(
                                containerColor = if (sexo == "Masculino")
                                    Color(0xFF374151).copy(alpha = 0.05f)
                                else
                                    Color(0xFFFAFAFA)
                            ),
                            border = if (sexo == "Masculino")
                                BorderStroke(2.dp, Color(0xFF374151))
                            else
                                BorderStroke(1.dp, Color(0xFFE5E7EB)),
                            shape = RoundedCornerShape(6.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = sexo == "Masculino",
                                    onClick = { sexo = "Masculino" },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Color(0xFF374151),
                                        unselectedColor = Color(0xFF9CA3AF)
                                    )
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Masculino",
                                    fontWeight = FontWeight.Medium,
                                    color = Color(0xFF374151),
                                    fontSize = 14.sp
                                )
                            }
                        }

                        // Opción Femenino
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .selectable(
                                    selected = sexo == "Femenino",
                                    onClick = { sexo = "Femenino" }
                                ),
                            colors = CardDefaults.cardColors(
                                containerColor = if (sexo == "Femenino")
                                    Color(0xFF374151).copy(alpha = 0.05f)
                                else
                                    Color(0xFFFAFAFA)
                            ),
                            border = if (sexo == "Femenino")
                                BorderStroke(2.dp, Color(0xFF374151))
                            else
                                BorderStroke(1.dp, Color(0xFFE5E7EB)),
                            shape = RoundedCornerShape(6.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = sexo == "Femenino",
                                    onClick = { sexo = "Femenino" },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Color(0xFF374151),
                                        unselectedColor = Color(0xFF9CA3AF)
                                    )
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Femenino",
                                    fontWeight = FontWeight.Medium,
                                    color = Color(0xFF374151),
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botones de acción con diseño corporativo
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Botón limpiar - resetea todos los campos
                OutlinedButton(
                    onClick = {
                        // Limpio todos los campos del formulario
                        nombre = ""
                        apellidoPaterno = ""
                        apellidoMaterno = ""
                        dia = ""
                        mes = ""
                        anio = ""
                        sexo = "Masculino"
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(1.dp, Color(0xFF6B7280))
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Limpiar",
                        modifier = Modifier.size(18.dp),
                        tint = Color(0xFF6B7280)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Limpiar",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = Color(0xFF6B7280)
                    )
                }

                // Botón siguiente - solo se habilita si todos los campos están llenos
                Button(
                    onClick = {
                        // Creo el objeto UserInfo con toda la información y navego al examen
                        val newUserInfo = UserInfo(
                            nombre = nombre,
                            apellidoPaterno = apellidoPaterno,
                            apellidoMaterno = apellidoMaterno,
                            dia = dia,
                            mes = mes,
                            anio = anio,
                            sexo = sexo
                        )
                        onUserInfoChange(newUserInfo)
                        navController.navigate("examen")
                    },
                    enabled = todosLosCamposLlenos, // Solo habilito si todos los campos están completos
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1F2937),
                        contentColor = Color.White,
                        disabledContainerColor = Color(0xFFD1D5DB),
                        disabledContentColor = Color(0xFF9CA3AF)
                    )
                ) {
                    Text(
                        text = "Continuar",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Siguiente",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}