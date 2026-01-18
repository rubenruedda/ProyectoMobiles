package com.infosport.compose.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "evento")
data class Evento(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "partido_id") val partidoId: Int,
    val minuto: Int,
    val tipo: String, // "Gol", "Tarjeta Amarilla", "Tarjeta Roja", "Sustitución"
    @ColumnInfo(name = "equipo_id") val equipoId: Int,
    @ColumnInfo(name = "nombre_jugador") val nombreJugador: String
)
