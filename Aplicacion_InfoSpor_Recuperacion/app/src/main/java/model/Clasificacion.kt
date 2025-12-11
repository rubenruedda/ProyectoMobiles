package model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "clasificacion",
    primaryKeys = ["liga_id", "equipo_id"], // Clave compuesta
    foreignKeys = [
        ForeignKey(entity = Liga::class, parentColumns = ["id"], childColumns = ["liga_id"]),
        ForeignKey(entity = Equipo::class, parentColumns = ["id"], childColumns = ["equipo_id"])
    ]
)
data class Clasificacion(
    @ColumnInfo(name = "liga_id", index = true)
    val ligaId: String,

    @ColumnInfo(name = "equipo_id", index = true)
    val equipoId: Int,

    // Datos desnormalizados del equipo para la UI
    val nombreEquipo: String,
    val escudoEquipo: String?,

    val posicion: Int,
    val puntos: Int,

    @ColumnInfo(name = "partidos_jugados")
    val partidosJugados: Int,
    val ganados: Int,
    val empatados: Int,
    val perdidos: Int
) : java.io.Serializable