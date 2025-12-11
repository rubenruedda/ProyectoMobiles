package model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "jugador")
data class Jugador(
    @PrimaryKey val id: Int,
    val nombre: String,
    @ColumnInfo(name = "equipo_id") val equipoId: Int, // FK a Equipo.id
    val posicion: String
) : Serializable