package model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "evento")
data class Evento(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "partido_id") val partidoId: Int, // FK a Partido.id
    val minuto: Int,
    val tipo: String, // "Gol", "Tarjeta Amarilla", "Sustitución"
    @ColumnInfo(name = "jugador_id") val jugadorId: Int, // FK a Jugador.id
    val nombreJugador: String // Desnormalizado para la UI
) : java.io.Serializable