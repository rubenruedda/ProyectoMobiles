package model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Partido",
    foreignKeys = [
        ForeignKey(
            entity = Liga::class,
            parentColumns = ["id"],
            childColumns = ["liga_id"]
        ),
        ForeignKey(
            entity = Equipo::class,
            parentColumns = ["id"],
            childColumns = ["equipo_local_id"]
        ),
        ForeignKey(
            entity = Equipo::class,
            parentColumns = ["id"],
            childColumns = ["equipo_visitante_id"]
        )
    ]
)
data class Partido(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "liga_id", index = true)
    val ligaId: String,

    @ColumnInfo(name = "equipo_local_id", index = true)
    val equipoLocalId: Int,

    @ColumnInfo(name = "equipo_visitante_id", index = true)
    val equipoVisitanteId: Int,

    // Nombres de equipo (desnormalizados para UI más rápida)
    val nombreLocal: String,
    val nombreVisitante: String,
    val escudoLocal: String?,
    val escudoVisitante: String?,

    @ColumnInfo(name = "marcador_local")
    val marcadorLocal: String? = null, // Nulo si el partido no se ha jugado

    @ColumnInfo(name = "marcador_visitante")
    val marcadorVisitante: String? = null,

    val fecha: String, // Formato "dd/MM/yyyy" o ISO
    val hora: String // Formato "HH:mm"
) : java.io.Serializable