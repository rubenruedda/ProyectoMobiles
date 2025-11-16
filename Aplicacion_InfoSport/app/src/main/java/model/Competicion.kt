package model

data class Competicion(
    val titulo: String, // Ej: "Partidos de Hoy"
    val partidos: List<Partido>
) : java.io.Serializable