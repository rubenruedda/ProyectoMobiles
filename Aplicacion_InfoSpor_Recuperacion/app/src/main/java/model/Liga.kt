package model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "liga")
data class Liga(
    @PrimaryKey
    val id: String, // String para coincidir con PreCargaDatos
    val nombre: String,
    @ColumnInfo(name = "pais_nombre") val paisNombre: String,
    @ColumnInfo(name = "logo_url") val logoUrl: String? = null,
    @ColumnInfo(name = "es_favorita") var esFavorita: Boolean = false
) : Serializable