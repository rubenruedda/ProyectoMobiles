package com.infosport.compose.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "clasificacion", primaryKeys = ["liga_id", "equipo_id"])
data class Clasificacion(
    @ColumnInfo(name = "liga_id") val ligaId: String,
    @ColumnInfo(name = "equipo_id") val equipoId: Int,
    @ColumnInfo(name = "nombre_equipo") val nombreEquipo: String,
    @ColumnInfo(name = "escudo_url") val escudoUrl: String? = null,
    val posicion: Int,
    val puntos: Int,
    @ColumnInfo(name = "partidos_jugados") val partidosJugados: Int,
    @ColumnInfo(name = "partidos_ganados") val partidosGanados: Int,
    @ColumnInfo(name = "partidos_empatados") val partidosEmpatados: Int,
    @ColumnInfo(name = "partidos_perdidos") val partidosPerdidos: Int
)
