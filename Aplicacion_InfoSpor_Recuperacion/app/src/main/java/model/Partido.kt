package model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "partido")
data class Partido(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "liga_id") val ligaId: String,
    @ColumnInfo(name = "equipo_local_id") val equipoLocalId: Int,
    @ColumnInfo(name = "equipo_visitante_id") val equipoVisitanteId: Int,
    val nombreLocal: String,
    val nombreVisitante: String,
    val escudoLocal: String?,
    val escudoVisitante: String?,
    @ColumnInfo(name = "marcador_local") val marcadorLocal: String? = null,
    @ColumnInfo(name = "marcador_visitante") val marcadorVisitante: String? = null,
    val fecha: String, // Formato "dd/MM/yyyy"
    val hora: String
) : Serializable {
    // Propiedades helper para la UI
    val golesLocal get() = marcadorLocal
    val golesVisitante get() = marcadorVisitante
}