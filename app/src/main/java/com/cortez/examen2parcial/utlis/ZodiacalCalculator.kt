package com.cortez.examen2parcial.utlis

object ZodiacalCalculator {

    fun calcularSignoZodiacalChino(anio: Int): String {
        val signos = arrayOf(
            "Mono", "Gallo", "Perro", "Cerdo", "Rata", "Buey",
            "Tigre", "Conejo", "Dragón", "Serpiente", "Caballo", "Cabra"
        )
        return signos[anio % 12]
    }

    fun calcularEdad(anio: Int): Int {
        val anioActual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)
        return anioActual - anio
    }

    fun getImagenSigno(signo: String): String {
        return when (signo.lowercase()) {
            "rata" -> "🐭"
            "buey" -> "🐂"
            "tigre" -> "🐅"
            "conejo" -> "🐰"
            "dragón" -> "🐉"
            "serpiente" -> "🐍"
            "caballo" -> "🐎"
            "cabra" -> "🐐"
            "mono" -> "🐒"
            "gallo" -> "🐓"
            "perro" -> "🐕"
            "cerdo" -> "🐷"
            else -> "🐎"
        }
    }
}