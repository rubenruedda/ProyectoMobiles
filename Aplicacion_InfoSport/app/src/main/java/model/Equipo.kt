package model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "equipo",
    foreignKeys = [ForeignKey(
        entity = Liga::class,
        parentColumns = ["id"],
        childColumns = ["liga_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Equipo(
    @PrimaryKey
    val id: Int,
    val nombre: String,

    @ColumnInfo(name = "escudo_url")
    val escudoUrl: String? = null,

    @ColumnInfo(name = "liga_id", index = true)
    val ligaId: String, // FK a Liga.id

    @ColumnInfo(name = "es_favorito")
    var esFavorito: Boolean = false
) : java.io.Serializable