package model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noticia")
data class Noticia(
    @PrimaryKey
    val id: Int,
    val titulo: String,
    val cuerpo: String,
    val fuente: String,

    @ColumnInfo(name = "fecha_publicacion")
    val fechaPublicacion: String,

    @ColumnInfo(name = "url_imagen")
    val urlImagen: String?
) : java.io.Serializable