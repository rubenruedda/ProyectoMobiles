package model

data class Favorito (
    val id: String, // Puede ser un ligaId o equipoId
    val nombre: String,
    val infoAdicional: String,
    val esLiga: Boolean,
    val logoUrl: String?
) : java.io.Serializable