package com.infosport.compose.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "equipo")
data class Equipo(
    @PrimaryKey val id: Int,
    val nombre: String,
    @ColumnInfo(name = "escudo_url") val escudoUrl: String? = null,
    @ColumnInfo(name = "liga_id") val ligaId: String,
    @ColumnInfo(name = "es_favorito") var esFavorito: Boolean = false
)
