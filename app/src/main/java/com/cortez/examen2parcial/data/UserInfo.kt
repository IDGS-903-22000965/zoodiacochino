package com.cortez.examen2parcial.data
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserInfo(
    var nombre: String = "",
    var apellidoPaterno: String = "",
    var apellidoMaterno: String = "",
    var dia: String = "",
    var mes: String = "",
    var anio: String = "",
    var sexo: String = "Masculino"
) : Parcelable