package com.infosport.compose.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "partido")
data class Partido(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "liga_id") val ligaId: String,
    @ColumnInfo(name = "equipo_local_id") val equipoLocalId: Int,
    @ColumnInfo(name = "equipo_visitante_id") val equipoVisitanteId: Int,
    @ColumnInfo(name = "nombre_local") val nombreLocal: String,
    @ColumnInfo(name = "nombre_visitante") val nombreVisitante: String,
    @ColumnInfo(name = "escudo_local") val escudoLocal: String? = null,
    @ColumnInfo(name = "escudo_visitante") val escudoVisitante: String? = null,
    @ColumnInfo(name = "marcador_local") val marcadorLocal: String? = null,
    @ColumnInfo(name = "marcador_visitante") val marcadorVisitante: String? = null,
    val fecha: String,
    val hora: String,
    @ColumnInfo(name = "es_favorito") var esFavorito: Boolean = false
)
