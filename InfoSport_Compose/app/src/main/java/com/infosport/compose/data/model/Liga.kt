package com.infosport.compose.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "liga")
data class Liga(
    @PrimaryKey val id: String,
    val nombre: String,
    val pais: String,
    @ColumnInfo(name = "logo_url") val logoUrl: String? = null,
    @ColumnInfo(name = "es_favorita") var esFavorita: Boolean = false
)
