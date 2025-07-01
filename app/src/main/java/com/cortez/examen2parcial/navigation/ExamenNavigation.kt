// navigation/ExamenNavigation.kt
package com.cortez.examen2parcial.navigation

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cortez.examen2parcial.data.UserInfo
import com.cortez.examen2parcial.screens.ExamenScreen
import com.cortez.examen2parcial.screens.FormularioScreen
import com.cortez.examen2parcial.screens.ResultadosScreen

@Composable
fun ExamenNavigation() {
    val navController = rememberNavController()
    var userInfo by remember { mutableStateOf(UserInfo()) }
    var respuestasUsuario by remember { mutableStateOf(listOf<Int>()) }

    NavHost(
        navController = navController,
        startDestination = "formulario"
    ) {
        composable("formulario") {
            FormularioScreen(
                navController = navController,
                userInfo = userInfo,
                onUserInfoChange = { newUserInfo ->
                    userInfo = newUserInfo
                }
            )
        }

        composable("examen") {
            ExamenScreen(
                navController = navController,
                onRespuestasComplete = { respuestas ->
                    respuestasUsuario = respuestas
                }
            )
        }

        composable("resultados") {
            ResultadosScreen(
                userInfo = userInfo,
                respuestasUsuario = respuestasUsuario,
                navController = navController
            )
        }
    }
}