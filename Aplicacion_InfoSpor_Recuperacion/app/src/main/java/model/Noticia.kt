package model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "noticia")
data class Noticia(
    @PrimaryKey val id: Int,
    val titulo: String,
    val cuerpo: String,
    val fuente: String,
    @ColumnInfo(name = "fecha_publicacion") val fechaPublicacion: String,
    @ColumnInfo(name = "url_imagen") val urlImagen: String?,
    @ColumnInfo(name = "es_favorita") var esFavorita: Boolean = false
) : Serializable