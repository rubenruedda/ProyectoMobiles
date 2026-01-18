package com.infosport.compose.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "noticia")
data class Noticia(
    @PrimaryKey val id: Int,
    val titulo: String,
    val contenido: String,
    val fuente: String,
    @ColumnInfo(name = "fecha_publicacion") val fechaPublicacion: String,
    @ColumnInfo(name = "imagen_url") val imagenUrl: String? = null,
    @ColumnInfo(name = "es_favorita") var esFavorita: Boolean = false
)
