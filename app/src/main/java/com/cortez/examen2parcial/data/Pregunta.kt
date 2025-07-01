package com.cortez.examen2parcial.data

data class Pregunta(
    val pregunta: String,
    val opciones: List<String>,
    val respuestaCorrecta: Int
)