package model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "alineacion", primaryKeys = ["partido_id", "jugador_id"])
data class Alineacion(
    @ColumnInfo(name = "partido_id") val partidoId: Int, // FK a Partido.id
    @ColumnInfo(name = "jugador_id") val jugadorId: Int, // FK a Jugador.id
    @ColumnInfo(name = "equipo_id") val equipoId: Int,
    val nombreJugador: String, // Desnormalizado
    @ColumnInfo(name = "es_titular") val esTitular: Boolean,
    val dorsal: Int
) : java.io.Serializable