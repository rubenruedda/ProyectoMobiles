package model

data class Pais(
    val nombrePais: String,
    val ligas: List<Liga>
) : java.io.Serializable